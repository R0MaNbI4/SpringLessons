package ru.geekbrains.thymeleafdemo.service;

import org.springframework.stereotype.Service;
import ru.geekbrains.thymeleafdemo.model.Product;
import ru.geekbrains.thymeleafdemo.repository.CartRepository;

import java.util.List;

@Service
public class CartService {
    CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addToCart(Product product) {
        cartRepository.addToCart(product);
    }

    public List<Product> getProducts() {
        return cartRepository.getProducts();
    }
}
