package com.getjavajob.training.eldorado.kovarnev.shop.dao;

import com.getjavajob.training.eldorado.kovarnev.shop.common.Customer;
import com.getjavajob.training.eldorado.kovarnev.shop.common.Order;
import com.getjavajob.training.eldorado.kovarnev.shop.common.Position;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.ParseException;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.concurrent.ConcurrentParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dao-context.xml")
public class ConcurrentParserTest {

    private static List<Customer> expected;
    @Autowired
    private ConcurrentParser<Customer> concurrentParser;
    @Value(value = "customersList.xml")
    private InputStream customersXml;

    @BeforeClass
    public static void setUp() {
        expected = new ArrayList<>();
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
        for (int i = 0; i < 10; i++) {
            expected.add(customer);
        }
    }

    @Test
    public void parseListTest() throws ParseException {
        List<Customer> actual = concurrentParser.parseList(customersXml);
        Assert.assertEquals(actual, expected);
    }
}
