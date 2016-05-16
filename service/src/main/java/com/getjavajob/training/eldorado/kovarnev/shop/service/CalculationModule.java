package com.getjavajob.training.eldorado.kovarnev.shop.service;

import com.getjavajob.training.eldorado.kovarnev.shop.common.Customer;

import java.util.*;

public class CalculationModule {

    private Map<Customer, Double> customersAndOrdersSum;
    private List<Double> ordersPrice;
    private boolean isSortedOrdersPrice;
    private Double totalOrdersSum;

    public CalculationModule() {
        this.customersAndOrdersSum = new HashMap<>();
        this.ordersPrice = new ArrayList<>();
    }

    public Double getSumOfAllOrders() {
        totalOrdersSum = 0d;
        for (Double aDouble : ordersPrice) {
            totalOrdersSum += aDouble;
        }
        return totalOrdersSum;
    }

    public Double getMaxOrderPrice() {
        if (!isSortedOrdersPrice) {
            Collections.sort(ordersPrice);
            isSortedOrdersPrice = true;
        }
        return ordersPrice.get(ordersPrice.size() - 1);
    }

    public Double getMinOrderPrice() {
        if (!isSortedOrdersPrice) {
            Collections.sort(ordersPrice);
            isSortedOrdersPrice = true;
        }
        return ordersPrice.get(0);
    }

    public int getTotalOrdersCount() {
        return ordersPrice.size();
    }

    public Double getAvgOrdersPrice() {
        double result;
        if (totalOrdersSum != null) {
            result = totalOrdersSum;
        } else {
            result = getSumOfAllOrders();
        }
        return result / ordersPrice.size();
    }

    public Map.Entry<Customer, Double> getCustomerWithBiggestOrderPrice() {
        Map.Entry<Customer, Double> candidate = null;
        for (Map.Entry<Customer, Double> entry : customersAndOrdersSum.entrySet()) {
            if ((candidate == null) || (candidate.getValue() < entry.getValue())) {
                candidate = entry;
            }
        }
        return candidate;
    }

    public List<Customer> getFilteredByOrdersSumCustomers(double sumFilter) {
        List<Customer> customers = new ArrayList<>();
        for (Map.Entry<Customer, Double> entry : customersAndOrdersSum.entrySet()) {
            if (entry.getValue() > sumFilter) {
                customers.add(entry.getKey());
            }
        }
        return customers;
    }

    public Map<Customer, Double> getCustomersAndOrdersSum() {
        return customersAndOrdersSum;
    }

    public void setCustomersAndOrdersSum(Map<Customer, Double> customersAndOrdersSum) {
        this.customersAndOrdersSum = customersAndOrdersSum;
    }

    public List<Double> getOrdersPrice() {
        return ordersPrice;
    }

    public void setOrdersPrice(List<Double> ordersPrice) {
        this.ordersPrice = ordersPrice;
    }

}
