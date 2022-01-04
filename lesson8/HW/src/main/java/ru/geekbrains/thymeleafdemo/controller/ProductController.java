package ru.geekbrains.thymeleafdemo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.geekbrains.thymeleafdemo.model.Product;
import ru.geekbrains.thymeleafdemo.service.ProductService;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getProductsPage(Model model, @PathParam("page") Optional<Integer> page) {
        Pageable p = PageRequest.of(page.orElse(0), 10, Sort.by("id").ascending());
        model.addAttribute("products", productService.getPage(p));
        return "product_list";
    }

    @PostMapping("products/delete/{id}")
    public String deleteById (@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("products/add")
    public String getAddForm () {
        return "add_form";
    }

    @PostMapping("products/add")
    public String add(@Valid Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("products/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "update_form";
    }

    @PostMapping("products/update/{id}")
    public String update(@PathVariable Long id, @Valid Product product) {
        product.setId(id);
        productService.save(product);
        return "redirect:/products";
    }
}
