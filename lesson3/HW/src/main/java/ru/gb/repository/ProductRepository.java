package ru.gb.repository;

import org.springframework.stereotype.Repository;
import ru.gb.dto.Product;
import ru.gb.dto.ProductFilter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
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
        products.add(new Product(2599, "WD Blue", "WD", 500, 5400, 64));
        products.add(new Product(2699, "WD Blue", "WD", 500, 7200, 32));
        products.add(new Product(2699, "Toshiba P300", "Toshiba", 500, 7200, 32));
        products.add(new Product(2850, "WD Blue", "WD", 1000, 7200, 64));
        products.add(new Product(2850, "Seagate Barracuda", "Seagate", 1000, 7200, 64));
        products.add(new Product(3059, "Toshiba S300 Surveillance", "Toshiba", 1000, 5700, 64));
        products.add(new Product(3499, "Seagate 5900 Ironwolf", "Seagate", 1000, 5900, 64));
        products.add(new Product(3899, "Toshiba P300", "Toshiba", 2000, 5400, 128));
    }

    public List<Product> getAll() {
        return products;
    }

    public List<Product> findProductsByFilter(ProductFilter filter) {
        return products.stream()
                .filter(product ->
                        product.getPrice() >= filter.getMinPrice()
                        && product.getPrice() <= filter.getMaxPrice()
                        && product.getSize() >= filter.getMinSize()
                        && product.getSize() <= filter.getMaxSize()
                        && product.getRotationSpeed() >= filter.getMinRotationSpeed()
                        && product.getRotationSpeed() <= filter.getMaxRotationSpeed()
                        && product.getCacheSize() >= filter.getMinCacheSize()
                        && product.getCacheSize() <= filter.getMaxCacheSize())
                .collect(Collectors.toList());
    }

    public void save(Product product) {
        products.add(product);
    }
}
