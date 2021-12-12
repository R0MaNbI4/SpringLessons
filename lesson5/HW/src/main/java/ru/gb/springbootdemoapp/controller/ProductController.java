package ru.gb.springbootdemoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootdemoapp.dto.Filter;
import ru.gb.springbootdemoapp.dto.Product;
import ru.gb.springbootdemoapp.service.ProductService;

import java.util.Map;

@Controller
public class ProductController {
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"/products", "/"})
    public String getAllProducts(Model model, Filter filter, @RequestParam Map<String, String> parameters) {
        if (parameters.isEmpty()) {
            model.addAttribute("products", productService.getAll());
        } else {
            model.addAttribute("products", productService.getFiltered(filter));
            model.addAttribute("parameters", parameters);
        }
        return "product_list";
    }

    @GetMapping("/add")
    public String getProductAddForm() {
        return "add_product";
    }

    @PostMapping("/add")
    public String addProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProductById(@PathVariable int id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }
}
