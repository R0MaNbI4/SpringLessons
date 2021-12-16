package ru.geekbrains.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetails {

    @EmbeddedId
    private OrderDetailsKey entryId;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "product_count")
    private Integer count;

    @Column(name = "price")
    private Integer price;

    public OrderDetails(Order order, Product product, Integer count, Integer price) {
        this.order = order;
        this.product = product;
        this.count = count;
        this.price = price;
    }

    public OrderDetails() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public OrderDetailsKey getEntryId() {
        return entryId;
    }

    public void setEntryId(OrderDetailsKey entryId) {
        this.entryId = entryId;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "product=" + product +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
