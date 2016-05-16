package com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser;

import com.getjavajob.training.eldorado.kovarnev.shop.common.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@Scope(value = "prototype")
public class CustomerXmlParser implements XmlParser<Customer> {

    private CustomerSaxHandler handler;
    private SAXParser parser;

    @Autowired
    public CustomerXmlParser(SAXParserFactory saxParserFactory, CustomerSaxHandler handler) {
        this.handler = handler;
        try {
            parser = saxParserFactory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer parse(InputStream is) throws ParseException {
        try {
            parser.parse(is, handler);
        } catch (SAXException | IOException e) {
            throw new ParseException("parseList exception", e);
        }
        return handler.getResult();
    }

    @Override
    public List<Customer> parseList(InputStream is) throws ParseException {
        throw new UnsupportedOperationException("no realization of this method");
    }
}
