package ru.geekbrains.service;

import org.springframework.stereotype.Service;
import ru.geekbrains.dao.CustomerDao;
import ru.geekbrains.dao.ProductDao;
import ru.geekbrains.dto.ProductCustomer;
import ru.geekbrains.model.Customer;
import ru.geekbrains.model.Order;
import ru.geekbrains.model.Product;

import java.util.HashMap;
import java.util.List;

@Service
public class ProductService {
    ProductDao productDao;

    ProductService (ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product findProductById(Long id) {
        return productDao.findProductById(id);
    }

    public List<ProductCustomer> getProductCustomers(Long id) {
        return productDao.getProductCustomers(id);
    }
}
