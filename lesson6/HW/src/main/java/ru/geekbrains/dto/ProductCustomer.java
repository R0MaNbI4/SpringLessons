package ru.geekbrains.dto;

import ru.geekbrains.model.Customer;

public class ProductCustomer {
    Customer customer;
    Long count;

    public ProductCustomer(Customer customer, Long count) {
        this.customer = customer;
        this.count = count;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ProductCustomer{" +
                "customer=" + customer.getName() +
                ", count=" + count +
                '}';
    }
}
