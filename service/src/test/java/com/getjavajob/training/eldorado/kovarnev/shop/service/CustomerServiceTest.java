package com.getjavajob.training.eldorado.kovarnev.shop.service;

import com.getjavajob.training.eldorado.kovarnev.shop.common.Customer;
import com.getjavajob.training.eldorado.kovarnev.shop.common.Order;
import com.getjavajob.training.eldorado.kovarnev.shop.common.Position;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.DAOException;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.dao.CustomerXmlDAO;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    private static List<Customer> customerList;
    private static Customer customer;
    @InjectMocks
    private CustomerService service;
    @Mock
    private CustomerXmlDAO dao;
    @Mock
    private InputStream customersXml;

    @BeforeClass
    public static void setUp() {
        customerList = new ArrayList<>();
        customer = new Customer();
        customer.setId(233658);
        customer.setName("Игорь Владимирович");

        Order order = new Order();
        order.setId(233658);

        Position position = new Position();
        position.setId(233658);
        position.setPrice(30.0);
        position.setCount(5);

        order.getPositions().add(position);

        customer.getOrders().add(order);
        customerList.add(customer);
    }

    @Test
    public void getAllTest() throws DAOException {
        service.getAll(customersXml);
        verify(dao).getAll(Matchers.eq(customersXml));
        verify(dao, times(1)).getAll(customersXml);

        when(dao.getAll(customersXml)).thenReturn(customerList);
        Assert.assertEquals(service.getAll(customersXml), customerList);
    }

    @Test
    public void calculateCustomersInfoTest() {
        CalculationModule module = service.calculateCustomersInfo(customerList);

        int expectedSize = 1;
        int actualSize = module.getCustomersAndOrdersSum().size();
        Assert.assertEquals(actualSize, expectedSize);

        Customer expectedCustomer = customer;
        Double expectedOrdersSum = 150d;
        Customer actualCustomer = null;
        Double actualOrdersSum = null;
        for (Map.Entry<Customer, Double> entry : module.getCustomersAndOrdersSum().entrySet()) {
            actualCustomer = entry.getKey();
            actualOrdersSum = entry.getValue();
        }
        Assert.assertEquals(actualCustomer, expectedCustomer);
        Assert.assertEquals(actualOrdersSum, expectedOrdersSum);

        int expectedOrderPriceSize = 1;
        int actualOrderPriceSize = module.getOrdersPrice().size();
        Assert.assertEquals(actualOrderPriceSize, expectedOrderPriceSize);

        Double expectedOrderPrice = module.getOrdersPrice().get(0);
        Double actualOrderPrice = 150d;
        Assert.assertEquals(actualOrderPrice, expectedOrderPrice);
    }
}
