package com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.entity;

import com.getjavajob.training.eldorado.kovarnev.shop.common.BaseEntity;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractEntityParser<T extends BaseEntity> implements EntityParser<T> {

    private AbstractHandler<T> handler;
    private SAXParser parser;

    public AbstractEntityParser(SAXParserFactory saxParserFactory) {
        this.handler = getHandler();
        try {
            parser = saxParserFactory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T parse(InputStream is) throws ParseException {
        try {
            parser.parse(is, handler);
        } catch (SAXException | IOException e) {
            throw new ParseException("parseList exception", e);
        }
        return handler.getResult();
    }

    @Override
    public abstract AbstractHandler<T> getHandler();
}
