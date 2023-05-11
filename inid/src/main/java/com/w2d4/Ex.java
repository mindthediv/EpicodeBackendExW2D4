package com.w2d4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Ex {
    static long prodId = 0;
    public static long prodCounter(long x){
      x = x + 1;
       return x;
    }
    static long orderId = 0;
    public static long orderCounter(long x){
      x = x + 1;
       return x;
    }
    static long custId = 0;
    public static long custCounter(long x){
       x = x + 1;
       return x;
    }
    public static void main(String[] args) {
      Logger log = LoggerFactory.getLogger(Ex.class);
      //Lista prodotti
      Stream<Product> prodList = Stream.<Product>builder()
     .add(new Product(prodCounter(prodId), "book1", "Books",(double) (Math.random()*99)))
     .add(new Product(prodCounter(prodId), "book2", "Books",(double) (Math.random()*99)))
     .add(new Product(prodCounter(prodId), "book3", "Books",(double) 150))
     .add(new Product(prodCounter(prodId), "Baby Monitor", "Baby",(double) (Math.random()*99)))
     .add(new Product(prodCounter(prodId), "Space Game", "Baby",(double) (Math.random()*99)))
     .add(new Product(prodCounter(prodId), "Diapers", "Baby",(double) (Math.random()*99)))
     .add(new Product(prodCounter(prodId), "Jeans", "Boys",(double) (Math.random()*99)))
     .add(new Product(prodCounter(prodId), "T-shirt", "Boys",(double) (Math.random()*99)))
     .add(new Product(prodCounter(prodId), "Shoes", "Boys",(double) (Math.random()*99))).build();

      //lista libri sopra i 100$
      Stream<Product> booksListOver100 =  prodList
      .filter(el ->((Product) el).getCategory().contains("Books"))
      .filter(el -> ((Product)el).getPrice() > 100);
      booksListOver100.forEach(el -> System.out.println(el.category +'-'+ Math.floor(el.price)+"$"));//

      //Lista ordini aventi prodotti "Baby"
      List<Product> list1 = new ArrayList<Product>();
      list1.add(new Product(prodCounter(prodId), "prod1", "Boys",(double) (Math.random()*99)));
      list1.add(new Product(prodCounter(prodId), "prod2", "Baby",(double) (Math.random()*99)));
      List<Product> list2 = new ArrayList<Product>();
      list1.add(new Product(prodCounter(prodId), "prod3", "Books",(double) (Math.random()*99)));
      list1.add(new Product(prodCounter(prodId), "prod4", "Baby",(double) (Math.random()*99)));
      List<Product> list3 = new ArrayList<Product>();
      list1.add(new Product(prodCounter(prodId), "prod5", "Boys",(double) (Math.random()*99)));
      list1.add(new Product(prodCounter(prodId), "prod6", "Books",(double) (Math.random()*99)));

      Stream<Order> ordersList = Stream.<Order>builder()
      .add(new Order(orderId, list1 , new Customer(custId, "Bill", 1)))
      .add(new Order(orderId, list2 , new Customer(custId, "Jim", 1)))
      .add(new Order(orderId, list3 , new Customer(custId, "Will", 2))).build();
      
      ordersList.forEach(order -> {
         if(order.getProducts().stream().anyMatch(prod -> prod.category == "Baby")) {
            System.out.println("(Con prodotti 'Baby') Ordine di: " + order.customer.toString());
         } else{
            System.out.println("(Senza prodotti 'Baby') Ordine di: " + order.customer.toString());
         }
      });
      Stream<Product> anotherProdStream = prodList;
      Stream<Product> boysList =  anotherProdStream
      .filter(el ->((Product) el).getCategory().contains("Boys"));
      boysList.forEach(el -> log.info("Prod1 -> $"+Math.floor(el.price)));
      boysList.forEach(el -> el.price = el.price - (el.price / 100)*10);
      boysList.forEach(el -> log.info("Prod1 -> $"+Math.floor(el.price)));      
    }
}
