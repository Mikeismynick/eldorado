package com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser;

import com.getjavajob.training.eldorado.kovarnev.shop.common.BaseEntity;

import java.io.InputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class ParseTask<T extends BaseEntity> implements Callable<T> {

    private InputStream xmlToParse;
    private BlockingQueue<XmlParser<T>> parserPool;

    public ParseTask(InputStream xmlToParse, BlockingQueue<XmlParser<T>> parserPool) {
        this.xmlToParse = xmlToParse;
        this.parserPool = parserPool;
    }

    @Override
    public T call() throws Exception {
        XmlParser<T> xmlParser = parserPool.take();
        T result = null;
        try {
            result = xmlParser.parse(xmlToParse);
        } finally {
            parserPool.offer(xmlParser);
        }
        return result;
    }
}
