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
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.thymeleafdemo.dto.ProductDto;
import ru.geekbrains.thymeleafdemo.mapper.ProductMapper;
import ru.geekbrains.thymeleafdemo.model.Product;
import ru.geekbrains.thymeleafdemo.service.CartService;
import ru.geekbrains.thymeleafdemo.service.ProductService;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("products")
public class ProductController {
    ProductService productService;
    ProductMapper productMapper;
    CartService cartService;

    public ProductController(ProductService productService, ProductMapper productMapper, CartService cartService) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.cartService = cartService;
    }

    @GetMapping("")
    public String getProductsPage(Model model, Pageable pageable) {
        model.addAttribute("products", productService.getPage(pageable));
        return "product_list";
    }

    @GetMapping("all")
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        return "product_list";
    }

    @PostMapping("delete/{id}")
    public String deleteById (@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("add")
    public String getAddForm () {
        return "add_form";
    }

    @PostMapping("add")
    public String add(@Valid Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "update_form";
    }

    @PostMapping("update/{id}")
    public String update(@PathVariable Long id, @Valid ProductDto productDto) {
        productDto.setId(id);
        productService.save(productMapper.productDtoToProduct(productDto));
        return "redirect:/products";
    }

    @PostMapping("addToCart/{id}")
    public String addToCart(@PathVariable Long id) {
        productService.addToCart(id);
        return "redirect:/products/cart";
    }

    @GetMapping("cart")
    public String getCart(Model model) {
        model.addAttribute(
                "products",
                cartService.getProducts().stream().map(productMapper::productToProductDto).collect(Collectors.toList()));
        return "cart";
    }
}
