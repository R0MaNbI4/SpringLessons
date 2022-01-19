package ru.geekbrains.thymeleafdemo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.geekbrains.thymeleafdemo.dto.ProductDto;
import ru.geekbrains.thymeleafdemo.model.Product;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Mapping(source = "price", target = "price", qualifiedByName = "bigDecimalToString")
    public abstract Product productDtoToProduct(ProductDto productDto);

    @Mapping(source = "price", target = "price", qualifiedByName = "stringToBigDecimal")
    public abstract ProductDto productToProductDto(Product product);

    @Named("bigDecimalToString")
    public String bigDecimalToString(BigDecimal price) {
        return price.toString();
    }

    @Named("stringToBigDecimal")
    public BigDecimal stringToBigDecimal(String price) {
        return new BigDecimal(price);
    }
}
