package ru.geekbrains.lesson2;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
@Scope("prototype")
public class Cart {
    private HashMap<Product, Integer> products;
    ProductRepository productRepository;

    public Cart(ProductRepository productRepository) {
        products = new HashMap<>();
        this.productRepository = productRepository;
    }

    public void addProduct(int id) {
        Product product = productRepository.getProduct(id);
        if (!products.containsKey(product)) {
            products.put(product, 1);
        } else {
            products.put(product, products.get(product) + 1);
        }
    }

    public void addProduct(int id, int count) {
        for (int i = 0; i < count; i++) {
            addProduct(id);
        }
    }

    public boolean deleteProduct(int id) {
        Product product = productRepository.getProduct(id);
        if (products.containsKey(product)) {
            products.put(product, products.get(product) - 1);
        } else {
            return false;
        }
        if (products.get(product) <= 0) {
            products.remove(product);
        }
        return true;
    }

    public boolean deleteProduct(int id, int count) {
        Product product = productRepository.getProduct(id);
        if (count >= products.get(product)) {
            products.remove(product);
            return true;
        }
        for (int i = 0; i < count; i++) {
            if (!deleteProduct(id)) {
                return false;
            }
        }
        return true;
    }

    public int getProductCount(int id) {
        try {
            return products.get(productRepository.getProduct(id));
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public void printProducts(int count) {
        int counter = 0;
        System.out.println("id\t\ttitle\t\t\tprice\t\tcount\t\tcost\t\t");
        for (Map.Entry<Product, Integer> product : products.entrySet()) {
            System.out.println(String.format(
                    "%d\t\t%s\t\t%.2f\t\t%d\t\t%.2f",
                    product.getKey().getId(),
                    product.getKey().getTitle(),
                    product.getKey().getPrice(),
                    product.getValue(),
                    product.getKey().getPrice() * product.getValue()
            ));
            counter++;
            if (counter == count && counter != -1) {
                return;
            }
        }
    }

    public void printProducts() {
        printProducts(-1);
    }
}
