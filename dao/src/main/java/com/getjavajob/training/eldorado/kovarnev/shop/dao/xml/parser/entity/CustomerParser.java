package com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.entity;

import com.getjavajob.training.eldorado.kovarnev.shop.common.Customer;

import javax.xml.parsers.SAXParserFactory;

public class CustomerParser extends AbstractEntityParser<Customer> {

    public CustomerParser(SAXParserFactory saxParserFactory) {
        super(saxParserFactory);
    }

    @Override
    public AbstractHandler<Customer> getHandler() {
        return new CustomerHandler();
    }
}
