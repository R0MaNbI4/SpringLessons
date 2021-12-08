package ru.gb.springbootdemoapp.repository;



import org.springframework.stereotype.Repository;
import ru.gb.springbootdemoapp.dto.Filter;
import ru.gb.springbootdemoapp.dto.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    List<Product> products;

    public ProductRepository(ArrayList<Product> products) {
        this.products = products;
    }

    @PostConstruct
    public void init() {
        products.add(new Product(1, 2599, "WD Blue", "WD", 500, 5400, 64));
        products.add(new Product(2, 2699, "WD Blue", "WD", 500, 7200, 32));
        products.add(new Product(3, 2699, "Toshiba P300", "Toshiba", 500, 7200, 32));
        products.add(new Product(4, 2850, "WD Blue", "WD", 1000, 7200, 64));
        products.add(new Product(5, 2850, "Seagate Barracuda", "Seagate", 1000, 7200, 64));
        products.add(new Product(6, 3059, "Toshiba S300 Surveillance", "Toshiba", 1000, 5700, 64));
        products.add(new Product(7, 3499, "Seagate 5900 Ironwolf", "Seagate", 1000, 5900, 64));
        products.add(new Product(8, 3899, "Toshiba P300", "Toshiba", 2000, 5400, 128));
    }

    public List<Product> getAll() {
        return products;
    }

    public void deleteProductById(int id) {
        products.stream().filter(product -> product.getId() == id)
                .findFirst()
                .ifPresent(products::remove);
    }

    public Product getProductById(int id) {
        return products.stream().filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Product> getFiltered(Filter filter) {
        return products.stream().filter(product ->
                        inRange(product.getPrice(), filter.getMinPrice(), filter.getMaxPrice())
                        && inRange(product.getSize(), filter.getMinSize(), filter.getMaxSize())
                        && inRange(product.getRotationSpeed(), filter.getMinRotationSpeed(), filter.getMaxRotationSpeed())
                        && inRange(product.getCacheSize(), filter.getMinCacheSize(), filter.getMaxCacheSize()))
                .collect(Collectors.toList());
    }

    public void save(Product product) {
        product.setId(getMaxId() + 1);
        products.add(product);
    }

    private boolean inRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    private int getMaxId() {
        int max = Integer.MIN_VALUE;
        for (Product product : products) {
            if (product.getId() > max) {
                max = product.getId();
            }
        }
        return max;
    }
}
