package ru.gb.springbootdemoapp.dto;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

public class Filter {
    int minPrice;
    int maxPrice;
    int minSize;
    int maxSize;
    int minRotationSpeed;
    int maxRotationSpeed;
    int minCacheSize;
    int maxCacheSize;
    List<String> brand;

    public Filter() {
        minPrice = 0;
        maxPrice = Integer.MAX_VALUE;
        minSize = 0;
        maxSize = Integer.MAX_VALUE;
        minRotationSpeed = 0;
        maxRotationSpeed = Integer.MAX_VALUE;
        minCacheSize = 0;
        maxCacheSize = Integer.MAX_VALUE;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinSize() {
        return minSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getMinRotationSpeed() {
        return minRotationSpeed;
    }

    public void setMinRotationSpeed(int minRotationSpeed) {
        this.minRotationSpeed = minRotationSpeed;
    }

    public int getMaxRotationSpeed() {
        return maxRotationSpeed;
    }

    public void setMaxRotationSpeed(int maxRotationSpeed) {
        this.maxRotationSpeed = maxRotationSpeed;
    }

    public int getMinCacheSize() {
        return minCacheSize;
    }

    public void setMinCacheSize(int minCacheSize) {
        this.minCacheSize = minCacheSize;
    }

    public int getMaxCacheSize() {
        return maxCacheSize;
    }

    public void setMaxCacheSize(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
    }

    public List<String> getBrand() {
        return brand;
    }

    public void setBrand(List<String> brand) {
        this.brand = brand;
    }
}
