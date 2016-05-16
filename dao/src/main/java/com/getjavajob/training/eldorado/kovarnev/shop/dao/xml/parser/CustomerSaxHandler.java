package com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser;

import com.getjavajob.training.eldorado.kovarnev.shop.common.Customer;
import com.getjavajob.training.eldorado.kovarnev.shop.common.Order;
import com.getjavajob.training.eldorado.kovarnev.shop.common.Position;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

@Component
@Scope("prototype")
public class CustomerSaxHandler extends DefaultHandler {

    private Customer customer;
    private Order order;
    private Position position;
    private String content;
    private int depth;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "customer":
                customer = new Customer();
                break;
            case "order":
                order = new Order();
                depth++;
                break;
            case "position":
                position = new Position();
                depth++;
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "id":
                switch (depth) {
                    case 0:
                        customer.setId(Long.valueOf(content));
                        break;
                    case 1:
                        order.setId(Long.valueOf(content));
                        break;
                    case 2:
                        position.setId(Long.valueOf(content));
                        break;
                }
                break;
            case "name":
                customer.setName(content);
                break;
            case "price":
                position.setPrice(Double.valueOf(content));
                break;
            case "count":
                position.setCount(Integer.valueOf(content));
                break;
            case "position":
                order.getPositions().add(position);
                depth--;
                break;
            case "order":
                customer.getOrders().add(order);
                depth--;
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        content = String.copyValueOf(ch, start, length).trim();
    }

    public Customer getResult() {
        return customer;
    }
}
