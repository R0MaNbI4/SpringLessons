package ru.geekbrains;

public class Product {
    private int id;
    private float cost;
    private String title;

    public Product(int id, float cost, String title) {
        this.id = id;
        this.cost = cost;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public float getCost() {
        return cost;
    }

    public String getTitle() {
        return title;
    }
}
