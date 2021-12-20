package ru.geekbrains.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.model.Product;
import ru.geekbrains.service.ProductService;

import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProductRestController {
    ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/rest/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id).orElse(null);
    }

    @GetMapping("/products/rest")
    public List<Product> getAll(
            @PathParam("minPrice") BigDecimal minPrice,
            @PathParam("maxPrice") BigDecimal maxPrice) {
        if (minPrice == null && maxPrice == null) {
            return productService.getAll();
        } else {
            return productService.getByPrice(minPrice, maxPrice);
        }
    }
}
