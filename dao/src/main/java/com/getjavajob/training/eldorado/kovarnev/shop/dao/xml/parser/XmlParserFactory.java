package com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser;

import com.getjavajob.training.eldorado.kovarnev.shop.common.BaseEntity;
import org.springframework.stereotype.Component;

@Component
public interface XmlParserFactory<T extends BaseEntity> {

    XmlParser<T> getParser();

}
