package ru.geekbrains.thymeleafdemo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.geekbrains.thymeleafdemo.model.Product;
import ru.geekbrains.thymeleafdemo.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    ProductRepository productRepository;
    CartService cart;

    public ProductService(ProductRepository productRepository, CartService cart) {
        this.productRepository = productRepository;
        this.cart = cart;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Page<Product> getPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Long getCount() {
        return productRepository.count();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    public void addToCart(Long id) {
        cart.addToCart(productRepository.getById(id));
    }
}
