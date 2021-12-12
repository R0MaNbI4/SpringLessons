package ru.gb.springbootdemoapp.dto;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "price")
    int price;
    @Column(name = "title")
    String title;
    @Column(name = "brand")
    String brand;
    @Column(name = "size")
    int size;
    @Column(name = "rotation_speed")
    int rotationSpeed;
    @Column(name = "cache_size")
    int cacheSize;

    public Product(int id, int price, String title, String brand, int size, int rotationSpeed, int cacheSize) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.brand = brand;
        this.size = size;
        this.rotationSpeed = rotationSpeed;
        this.cacheSize = cacheSize;
    }

    public Product() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
