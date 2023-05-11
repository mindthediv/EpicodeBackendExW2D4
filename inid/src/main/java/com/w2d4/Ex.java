package com.w2d4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Ex {
    
    public static long prodCounter(long x){
      x = x + 1;
       return x;
    }
    
    public static long orderCounter(long x){
      x = x + 1;
       return x;
    }
    
    public static long custCounter(long x){
       x = x + 1;
       return x;
    }
    public static void main(String[] args) {
      long prodId = 0;
      long orderId = 0;
      long custId = 0;
      Logger log = LoggerFactory.getLogger(Ex.class);
      //Lista prodotti
      List<Product> prodList = Stream.<Product>builder()
     .add(new Product(prodCounter(prodId), "book1", "Books",(double) (Math.random()*99)))
     .add(new Product(prodCounter(prodId), "book2", "Books",(double) (Math.random()*99)))
     .add(new Product(prodCounter(prodId), "book3", "Books",(double) 150))
     .add(new Product(prodCounter(prodId), "Baby Monitor", "Baby",(double) (Math.random()*99)))
     .add(new Product(prodCounter(prodId), "Space Game", "Baby",(double) (Math.random()*99)))
     .add(new Product(prodCounter(prodId), "Diapers", "Baby",(double) (Math.random()*99)))
     .add(new Product(prodCounter(prodId), "Jeans", "Boys",(double) (Math.random()*99)))
     .add(new Product(prodCounter(prodId), "T-shirt", "Boys",(double) (Math.random()*99)))
     .add(new Product(prodCounter(prodId), "Shoes", "Boys",(double) (Math.random()*99))).build().toList();

      //lista libri sopra i 100$
      Stream<Product> booksListOver100 =  prodList.stream()
      .filter(el ->((Product) el).getCategory().contains("Books"))
      .filter(el -> ((Product)el).getPrice() > 100);
      booksListOver100.forEach(el -> System.out.println(el.category +'-'+ Math.floor(el.price)+"$ "+ el.id));//

      //Lista ordini aventi prodotti "Baby"
      List<Product> list1 = new ArrayList<Product>();
      list1.add(prodList.get(0));
      list1.add(prodList.get(5));
      List<Product> list2 = new ArrayList<Product>();
      list2.add(prodList.get(5));
      list2.add(prodList.get(2));
      List<Product> list3 = new ArrayList<Product>();
      list3.add(prodList.get(7));
      list3.add(prodList.get(8));

      List<Order> ordersList = Stream.<Order>builder()
      .add(new Order(orderId, list1 , new Customer(custId, "Bill", 1)))
      .add(new Order(orderId, list2 , new Customer(custId, "Jim", 1)))
      .add(new Order(orderId, list3 , new Customer(custId, "Will", 2))).build().toList();
      
      List<Order> list = orderBaby(ordersList, "Baby");
      list.forEach(el -> System.out.println(el.toString()));
   }

     
        
   
   public static List<Order> orderBaby(List<Order> ordersList, String category){
      return ordersList.stream()
      .filter(ord -> ord.getProducts()
                           .stream()
                           .anyMatch(prod -> prod.getCategory().equalsIgnoreCase(category))
      ).toList()
      ;
   }
  

}
