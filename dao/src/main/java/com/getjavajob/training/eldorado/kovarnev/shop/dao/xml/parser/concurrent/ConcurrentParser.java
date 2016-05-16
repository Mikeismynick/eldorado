package com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.concurrent;

import com.getjavajob.training.eldorado.kovarnev.shop.common.BaseEntity;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.ParseException;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.entity.EntityParser;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
@Scope("prototype")
public interface ConcurrentParser<T extends BaseEntity> {

    List<T> parseList(InputStream is) throws ParseException;

    EntityParser<T> getEntityParser();
}
