package org.example.entity;

public class Goods extends Product {
    public int getRemainder() {
        return remainder;
    }

    public void setRemainder(int remainder) {
        this.remainder = remainder;
    }

    int remainder;

    public Goods(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.remainder = 1;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "remainder=" + remainder +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
