package com.w2d4;

import java.time.LocalDate;
import java.util.List;

public class Order extends HasId {
    
    String status = "Accepted";
    LocalDate orderDate = LocalDate.now();
    LocalDate deliveryDate = this.orderDate.plusDays(7);
    List<Product> products;
    Customer customer;

    public Order(long id, List<Product> products, Customer cust) {
        super(id);
        this.products = products;
        this.customer = cust;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return status + " " + customer.toString();
       
    }
}
