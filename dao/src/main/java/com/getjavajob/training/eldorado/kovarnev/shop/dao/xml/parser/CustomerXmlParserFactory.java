package com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser;

import com.getjavajob.training.eldorado.kovarnev.shop.common.Customer;

public abstract class CustomerXmlParserFactory implements XmlParserFactory<Customer> {

    @Override
    public abstract XmlParser<Customer> getParser();

}
