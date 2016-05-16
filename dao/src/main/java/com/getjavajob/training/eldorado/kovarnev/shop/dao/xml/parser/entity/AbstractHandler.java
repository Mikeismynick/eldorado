package com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.entity;

import com.getjavajob.training.eldorado.kovarnev.shop.common.BaseEntity;
import org.xml.sax.helpers.DefaultHandler;

public abstract class AbstractHandler<T extends BaseEntity> extends DefaultHandler {

    abstract public T getResult();
}
