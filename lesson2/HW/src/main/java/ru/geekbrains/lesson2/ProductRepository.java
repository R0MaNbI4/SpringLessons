package ru.geekbrains.lesson2;

import org.springframework.stereotype.Component;
import ru.geekbrains.lesson2.postprocessor.AddProduct;

import java.util.ArrayList;

@Component
public class ProductRepository {
    @AddProduct(count = 10)
    private ArrayList<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
    }

    ArrayList<Product> getProducts() {
        return products;
    }

    Product getProduct(int id) {
        return products.get(id);
    }
}
