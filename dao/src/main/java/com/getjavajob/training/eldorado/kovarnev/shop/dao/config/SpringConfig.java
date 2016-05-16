package com.getjavajob.training.eldorado.kovarnev.shop.dao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.transform.TransformerFactory;

@Configuration
public class SpringConfig {

    @Bean
    public SAXParserFactory saxParserFactory() {
        return SAXParserFactory.newInstance();
    }

    @Bean
    public XMLInputFactory xmlInputFactory() {
        return XMLInputFactory.newInstance();
    }

    @Bean
    public TransformerFactory transformerFactory() {
        return TransformerFactory.newInstance();
    }
}
