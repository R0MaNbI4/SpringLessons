package ru.gb.service;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import ru.gb.dto.Product;
import ru.gb.dto.ProductFilter;
import ru.gb.repository.ProductRepository;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public List<Product> getProductsByFilter(ProductFilter filter) {
        return productRepository.findProductsByFilter(filter);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void addProduct(Map<String, String> parameters) throws Exception {
        Product product = new Product();
        try {
            product.setPrice(Float.parseFloat(parameters.get("price")));
            product.setTitle(parameters.get("title"));
            product.setSize(Integer.parseInt(parameters.get("size")));
            product.setRotationSpeed(Integer.parseInt(parameters.get("rotationSpeed")));
            product.setCacheSize(Integer.parseInt(parameters.get("cacheSize")));
            product.setBrand(parameters.get("brand"));
            productRepository.save(product);
        } catch (Exception e) {
            throw e;
        }
    }

    public ProductFilter setFilter(Map<String, String> parameters) throws Exception {
        ProductFilter filter = new ProductFilter();
        try {
            for (String key : parameters.keySet()) {
                if (parameters.get(key) != null) {
                    Method method = ProductFilter.class.getMethod("set" + Character.toUpperCase(key.charAt(0)) + key.substring(1), String.class);
                    method.invoke(filter, parameters.get(key));
                }
            }
        } catch (Exception e) {
            throw e;
            //return new ProductFilter();
        }
        return filter;
    }
}
