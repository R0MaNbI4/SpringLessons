package ru.geekbrains.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.geekbrains.model.Product;
import ru.geekbrains.service.ProductService;

import java.util.Map;

@Controller
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products/rest";
    }

    @GetMapping("/products/add")
    public String getAddForm() {
        return "add_form";
    }

    @PostMapping("/products/add")
    public String add(Product product) {
        Long id = productService.add(product).getId();
        return "redirect:/products/rest/" + id;
    }

    @GetMapping("/products/filter")
    public String getFilterForm() {
        return "filter_form";
    }

    @PostMapping("/products/filter")
    public ModelAndView getFiltered(@RequestParam Map<String, String> attributes, ModelMap model) {
        attributes.entrySet().stream()
                .filter(e -> !e.getValue().isBlank())
                .forEach(e -> model.put(e.getKey(), e.getValue()));
        return new ModelAndView("redirect:/products/rest", model);
    }
}
