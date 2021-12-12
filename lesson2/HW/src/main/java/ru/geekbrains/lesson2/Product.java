package ru.geekbrains.lesson2;

public class Product {
    private int id;
    private String title;
    private float price;

    public Product(int id, String title, float price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                String.format(", price=%.2f", price) +
                '}';
    }
}
