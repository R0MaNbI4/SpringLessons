package ru.geekbrains.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.geekbrains.model.Product;
import ru.geekbrains.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAll(Sort sort) {
        return productRepository.findAll(sort);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product add(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getByPrice(BigDecimal min, BigDecimal max) {
        if (min == null) {
            min = BigDecimal.valueOf(Double.MIN_VALUE);
        }
        if (max == null) {
            max = BigDecimal.valueOf(Double.MAX_VALUE);
        }
        return productRepository.getProductByPrice(min, max);
    }
}
