package ru.gb.springbootdemoapp.dto;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "price")
    Integer price;
    @Column(name = "title")
    String title;
    @Column(name = "brand")
    String brand;
    @Column(name = "size")
    Integer size;
    @Column(name = "rotation_speed")
    Integer rotationSpeed;
    @Column(name = "cache_size")
    Integer cacheSize;

    public Product(Integer price, String title, String brand, Integer size, Integer rotationSpeed, Integer cacheSize) {
        this.price = price;
        this.title = title;
        this.brand = brand;
        this.size = size;
        this.rotationSpeed = rotationSpeed;
        this.cacheSize = cacheSize;
    }

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(Integer rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public Integer getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(Integer cacheSize) {
        this.cacheSize = cacheSize;
    }
}
