package com.solvd.mydemoapp.mobile.dto;

import java.util.Objects;

public class Product {

    private String name;
    private String description;
    private double price;

    private int quantity;
    private int rate;

    private String color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && quantity == product.quantity && rate == product.rate && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(color, product.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, quantity, rate, color);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", rate=" + rate +
                ", color='" + color + '\'' +
                '}';
    }
}
