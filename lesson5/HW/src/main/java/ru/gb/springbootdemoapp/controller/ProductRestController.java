package ru.gb.springbootdemoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.springbootdemoapp.dto.Product;
import ru.gb.springbootdemoapp.service.ProductService;

import java.util.List;

@RestController
public class ProductRestController {
    ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/rest/{id}")
    public Product getRestProduct(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @GetMapping("/rest/all")
    public List<Product> getRestAll() {
        return productService.getAll();
    }
}
