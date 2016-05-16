package com.getjavajob.training.eldorado.kovarnev.shop.dao;

import com.getjavajob.training.eldorado.kovarnev.shop.common.Customer;
import com.getjavajob.training.eldorado.kovarnev.shop.common.Order;
import com.getjavajob.training.eldorado.kovarnev.shop.common.Position;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.ParseException;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.entity.CustomerParser;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.entity.EntityParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dao-context.xml")
public class CustomersTest {

    private static Customer expected;
    private EntityParser<Customer> parser = new CustomerParser(SAXParserFactory.newInstance());
    @Value(value = "customer.xml")
    private InputStream customerXml;

    @BeforeClass
    public static void setUp() {
        expected = new Customer();
        expected.setId(233658);
        expected.setName("Игорь Владимирович");
        Order order = new Order();
        order.setId(233658);
        Position position = new Position();
        position.setId(233658);
        position.setPrice(30.0);
        position.setCount(5);
        order.getPositions().add(position);
        expected.getOrders().add(order);
    }

    @Test
    public void parseTest() throws ParseException {
        Customer actual = parser.parse(customerXml);
        Assert.assertEquals(expected, actual);
    }
}
