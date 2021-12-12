package ru.gb.springbootdemoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.springbootdemoapp.dao.ProductDao;
import ru.gb.springbootdemoapp.dto.Filter;
import ru.gb.springbootdemoapp.dto.Product;

import java.util.List;

@Service
public class ProductService {
    ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao){
        this.productDao = productDao;
    }

    public List<Product> getAll() {
        return productDao.findAll();
    }

    public List<Product> getFiltered(Filter filter) {
        return productDao.findByParameters(filter);
    }

    public void deleteProductById(int id) {
        productDao.deleteById(id);
    }

    public Product getProductById(int id) {
        return productDao.findById(id);
    }

    public void saveProduct(Product product) {
        productDao.saveOrUpdate(product);
    }
}
