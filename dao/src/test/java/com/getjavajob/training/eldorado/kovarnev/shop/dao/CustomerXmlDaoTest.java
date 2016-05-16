package com.getjavajob.training.eldorado.kovarnev.shop.dao;


import com.getjavajob.training.eldorado.kovarnev.shop.common.Customer;
import com.getjavajob.training.eldorado.kovarnev.shop.common.Order;
import com.getjavajob.training.eldorado.kovarnev.shop.common.Position;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.dao.CustomerXmlDAO;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.ParseException;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.concurrent.ConcurrentParser;
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

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerXmlDaoTest {

    private static List<Customer> customerList;
    @InjectMocks
    private CustomerXmlDAO customerDAO;
    @Mock
    private ConcurrentParser<Customer> customerConcurrentParser;
    @Mock
    private InputStream customersXml;

    @BeforeClass
    public static void setUp() {
        customerList = new ArrayList<>();
        Customer customer = new Customer();
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
    public void getAllTest() throws DAOException, ParseException {
        customerDAO.getAll(customersXml);
        verify(customerConcurrentParser).parseList(Matchers.eq(customersXml));
        verify(customerConcurrentParser, times(1)).parseList(customersXml);

        when(customerConcurrentParser.parseList(customersXml)).thenReturn(customerList);
        Assert.assertEquals(customerDAO.getAll(customersXml), customerList);
    }
}
