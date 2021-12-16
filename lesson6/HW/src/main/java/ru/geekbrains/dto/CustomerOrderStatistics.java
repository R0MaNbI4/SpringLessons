package ru.geekbrains.dto;

import ru.geekbrains.model.Product;

public class CustomerOrderStatistics {
    private Product product;
    private long count;

    public CustomerOrderStatistics(Product product, long count) {
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ProductStatistics{" +
                "name='" + product.getTitle() + '\'' +
                ", count=" + count +
                '}';
    }
}
