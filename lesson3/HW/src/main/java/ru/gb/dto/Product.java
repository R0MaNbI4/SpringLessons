package ru.gb.dto;

public class Product {
    float price;
    String title;
    String brand;
    int size;
    int rotationSpeed;
    int cacheSize;

    public Product(float price, String title, String brand, int size, int rotationSpeed, int cacheSize) {
        this.price = price;
        this.title = title;
        this.brand = brand;
        this.size = size;
        this.rotationSpeed = rotationSpeed;
        this.cacheSize = cacheSize;
    }

    public Product() {
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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
}
