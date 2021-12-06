package ru.gb.dto;

import java.util.Arrays;
import java.util.List;

public class ProductFilter {
    float minPrice;
    float maxPrice;
    List<String> brand;
    int minSize;
    int maxSize;
    int minRotationSpeed;
    int maxRotationSpeed;
    int minCacheSize;
    int maxCacheSize;

    public ProductFilter() {
        minPrice = 0;
        maxPrice = Float.MAX_VALUE;
        minSize = 0;
        maxSize = Integer.MAX_VALUE;
        minRotationSpeed = 0;
        maxRotationSpeed = Integer.MAX_VALUE;
        minCacheSize = 0;
        maxCacheSize = Integer.MAX_VALUE;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = Float.parseFloat(minPrice);
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = Float.parseFloat(maxPrice);
    }

    public List<String> getBrand() {
        return brand;
    }

    public int getMinSize() {
        return minSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMinSize(String minSize) {
        this.minSize = Integer.parseInt(minSize);
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void setMaxSize(String maxSize) {
        this.maxSize = Integer.parseInt(maxSize);
    }

    public int getMinRotationSpeed() {
        return minRotationSpeed;
    }

    public void setMinRotationSpeed(int minRotationSpeed) {
        this.minRotationSpeed = minRotationSpeed;
    }

    public void setMinRotationSpeed(String minRotationSpeed) {
        this.minRotationSpeed = Integer.parseInt(minRotationSpeed);
    }

    public int getMaxRotationSpeed() {
        return maxRotationSpeed;
    }

    public void setMaxRotationSpeed(int maxRotationSpeed) {
        this.maxRotationSpeed = maxRotationSpeed;
    }

    public void setMaxRotationSpeed(String maxRotationSpeed) {
        this.maxRotationSpeed = Integer.parseInt(maxRotationSpeed);
    }

    public int getMinCacheSize() {
        return minCacheSize;
    }

    public void setMinCacheSize(int minCacheSize) {
        this.minCacheSize = minCacheSize;
    }

    public void setMinCacheSize(String minCacheSize) {
        this.minCacheSize = Integer.parseInt(minCacheSize);
    }

    public int getMaxCacheSize() {
        return maxCacheSize;
    }

    public void setMaxCacheSize(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
    }

    public void setMaxCacheSize(String maxCacheSize) {
        this.maxCacheSize = Integer.parseInt(maxCacheSize);
    }
}
