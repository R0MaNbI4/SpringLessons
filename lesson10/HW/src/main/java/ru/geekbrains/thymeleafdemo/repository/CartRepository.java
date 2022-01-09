package ru.geekbrains.thymeleafdemo.repository;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import ru.geekbrains.thymeleafdemo.model.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartRepository {
    List<Product> products = new ArrayList<>();

    public void addToCart(Product product) {
        products.add((Product) Hibernate.unproxy(product));
    }

    public List<Product> getProducts() {
        return products;
    }
}
