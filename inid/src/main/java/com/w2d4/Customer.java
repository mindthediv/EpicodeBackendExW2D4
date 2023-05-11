package com.w2d4;

public class Customer extends HasId {
    long id;
    String name;
    int tier;
    public Customer(long id, String name, int tier) {
        super(id);
        this.name = name;
        this.tier = tier;
    }
    @Override
    public String toString() {
        return this.name;
    }
}
