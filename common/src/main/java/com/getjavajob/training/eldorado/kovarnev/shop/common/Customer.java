package com.getjavajob.training.eldorado.kovarnev.shop.common;

import java.util.ArrayList;
import java.util.List;

public class Customer extends BaseEntity {

    private String name;
    private List<Order> orders;

    public Customer(String name) {
        this.name = name;
        this.orders = new ArrayList<>();
    }

    public Customer() {
        this.orders = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Customer customer = (Customer) o;

        if (!name.equals(customer.name)) return false;
        return orders.equals(customer.orders);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + orders.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", orders=" + orders +
                '}';
    }
}
