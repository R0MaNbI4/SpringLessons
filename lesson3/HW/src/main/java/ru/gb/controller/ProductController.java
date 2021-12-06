package ru.gb.controller;

import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.dto.Product;
import ru.gb.dto.ProductFilter;
import ru.gb.service.ProductService;

import java.util.Map;

@Controller
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getAllProducts(Model model,
                                 @RequestParam Map<String, String> parameters) throws Exception{
        if (parameters.isEmpty()) {
            model.addAttribute("products", productService.getAll());
        } else {
            model.addAttribute("products", productService.getProductsByFilter(
                productService.setFilter(parameters)));
        }
        //model.addAttribute("parameters", parameters);
        return "product_list";
    }

    @GetMapping("/add")
    public String getProductAddForm() {
        return "product_form";
    }

    @PostMapping("/add")
    public String saveProduct(@RequestParam Map<String, String> parameters) throws Exception {
        productService.addProduct(parameters);
        return "redirect:products";
    }

    @GetMapping("/")
    public String getMainPage() {
        return "index";
    }
}
