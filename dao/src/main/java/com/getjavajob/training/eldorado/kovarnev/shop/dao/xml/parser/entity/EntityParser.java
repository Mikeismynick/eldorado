package com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.entity;

import com.getjavajob.training.eldorado.kovarnev.shop.common.BaseEntity;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.ParseException;

import java.io.InputStream;

public interface EntityParser<T extends BaseEntity> {

    T parse(InputStream is) throws ParseException;

    AbstractHandler<T> getHandler();
}
