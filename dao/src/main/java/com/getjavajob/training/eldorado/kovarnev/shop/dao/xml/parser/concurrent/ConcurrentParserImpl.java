package com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.concurrent;

import com.getjavajob.training.eldorado.kovarnev.shop.common.Customer;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.entity.CustomerParser;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.entity.EntityParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.transform.TransformerFactory;

@Component
public class ConcurrentParserImpl extends AbstractConcurrentParser<Customer> {

    @Autowired
    public ConcurrentParserImpl(XMLInputFactory xif, TransformerFactory tf, SAXParserFactory spf) {
        super(xif, tf, spf);
    }

    @Override
    public EntityParser<Customer> getEntityParser() {
        return new CustomerParser(spf);
    }
}
