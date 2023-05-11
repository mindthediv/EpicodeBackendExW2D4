package com.w2d4;

public class Product  extends HasId {
    String name;
    String category;
    double price;

    public String getCategory() {
        return category;
    }
    
    public double getPrice() {
        return price;
    }

    public Product(long id,String name, String category, double price){
        super(id);
        this.name = name;
        this.category = category;
        this.price = price;
    }
}
