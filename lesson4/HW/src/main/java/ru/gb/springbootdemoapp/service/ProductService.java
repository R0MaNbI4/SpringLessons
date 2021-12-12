package ru.gb.springbootdemoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.springbootdemoapp.dto.Filter;
import ru.gb.springbootdemoapp.dto.Product;
import ru.gb.springbootdemoapp.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public List<Product> getFiltered(Filter filter) {
        return productRepository.getFiltered(filter);
    }

    public void deleteProductById(int id) {
        productRepository.deleteProductById(id);
    }

    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }
}
