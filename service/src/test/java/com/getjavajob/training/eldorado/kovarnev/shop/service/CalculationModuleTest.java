package com.getjavajob.training.eldorado.kovarnev.shop.service;

import com.getjavajob.training.eldorado.kovarnev.shop.common.Customer;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

public class CalculationModuleTest {

    private static CalculationModule calculationModule;

    @BeforeClass
    public static void setUp() {
        calculationModule = new CalculationModule();

        List<Double> price = new ArrayList<>(Arrays.asList(500d, 1000d, 350.5d, 50d));
        calculationModule.setOrdersPrice(price);

        Map<Customer, Double> customers = new HashMap<>();
        Customer petr = new Customer("Petr");
        Customer ivan = new Customer("Ivan");
        Customer vladimir = new Customer("Vladimir");
        Customer vasya = new Customer("Vasya");
        customers.put(petr, 500d);
        customers.put(ivan, 1000d);
        customers.put(vladimir, 350.5d);
        customers.put(vasya, 50d);
        calculationModule.setCustomersAndOrdersSum(customers);
    }

    @Test
    public void getSumOfAllOrdersTest() {
        double expected = 1900.5;
        double actual = calculationModule.getSumOfAllOrders();

        Assert.assertEquals(expected, actual, 0.001);
    }

    @Test
    public void getMaxOrderPriceTest() {
        double expected = 1000;
        double actual = calculationModule.getMaxOrderPrice();

        Assert.assertEquals(expected, actual, 0.001);
    }

    @Test
    public void getMinOrderPriceTest() {
        double expected = 50;
        double actual = calculationModule.getMinOrderPrice();

        Assert.assertEquals(expected, actual, 0.001);
    }

    @Test
    public void getTotalOrdersCountTest() {
        int expected = 4;
        int actual = calculationModule.getTotalOrdersCount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAvgOrdersPriceTest() {
        double expected = 475.125;
        double actual = calculationModule.getAvgOrdersPrice();

        Assert.assertEquals(expected, actual, 0.001);
    }

    @Test
    public void getCustomerWithBiggestOrderPriceTest() {
        Customer expectedKey = new Customer("Ivan");
        Double expectedValue = 1000d;
        Map.Entry<Customer, Double> actual = calculationModule.getCustomerWithBiggestOrderPrice();

        Assert.assertEquals(expectedKey, actual.getKey());
        Assert.assertEquals(expectedValue, actual.getValue());
    }
}
