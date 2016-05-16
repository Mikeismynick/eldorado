package com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser;

import com.getjavajob.training.eldorado.kovarnev.shop.common.BaseEntity;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public interface XmlParser<T extends BaseEntity> {

    T parse(InputStream is) throws ParseException;

    List<T> parseList(InputStream is) throws ParseException;
}
