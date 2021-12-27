//package ru.geekbrains.thymeleafdemo.mapper;
//
//import ru.geekbrains.thymeleafdemo.dto.ProductDto;
//import ru.geekbrains.thymeleafdemo.model.Product;
//
//import java.math.BigDecimal;
//
//public class ProductMapper {
//    public static Product getModel(ProductDto dto) {
//        Product product = new Product();
//        product.setId(dto.getId());
//        product.setArticle(dto.getArticle());
//        product.setTitle(dto.getTitle());
//        product.setPrice(dto.getPrice());
//        product.setCategory(dto.getCategory());
//        product.setBrand(dto.getBrand());
//        product.setModel(dto.getModel());
//        product.setColor(dto.getColor());
//        product.setImageUrl(dto.getImageUrl());
//        return product;
//    }
//
//    public static ProductDto getDto(Product model) {
//        ProductDto dto = new ProductDto();
//        dto.setId(model.getId());
//        dto.setArticle(model.getArticle());
//        dto.setTitle(model.getTitle());
//        dto.setPrice(model.getPrice());
//        dto.setCategory(model.getCategory());
//        dto.setBrand(model.getBrand());
//        dto.setModel(model.getModel());
//        dto.setColor(model.getColor());
//        dto.setImageUrl(model.getImageUrl());
//        return dto;
//    }
//}
