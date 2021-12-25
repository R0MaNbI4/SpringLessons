package ru.geekbrains.service;

import org.springframework.stereotype.Service;
import ru.geekbrains.dao.CustomerDao;
import ru.geekbrains.dto.CustomerOrderStatistics;
import ru.geekbrains.model.Customer;
import ru.geekbrains.model.Order;
import ru.geekbrains.model.Product;

import java.util.HashMap;
import java.util.List;

@Service
public class CustomerService {
    CustomerDao customerDao;

    CustomerService (CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer findCustomerById(Long id) {
        return customerDao.findCustomerById(id);
    }

    public List<Order> getOrders(Long id) {
        return customerDao.getOrders(id);
    }

    public void addOrder(Customer customer, HashMap<Product, Integer> products) {
        customerDao.addOrder(customer, products);
    }

    public List<CustomerOrderStatistics> getProductStatistic(Long id) {
        return customerDao.getProductStatistics(id);
    }
}
