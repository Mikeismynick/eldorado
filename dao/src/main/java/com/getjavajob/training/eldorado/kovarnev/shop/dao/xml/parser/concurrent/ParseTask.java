package com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.concurrent;

import com.getjavajob.training.eldorado.kovarnev.shop.common.BaseEntity;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.entity.EntityParser;

import java.io.InputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class ParseTask<T extends BaseEntity> implements Callable<T> {

    private InputStream xmlToParse;
    private BlockingQueue<EntityParser<T>> parserPool;

    public ParseTask(InputStream xmlToParse, BlockingQueue<EntityParser<T>> parserPool) {
        this.xmlToParse = xmlToParse;
        this.parserPool = parserPool;
    }

    @Override
    public T call() throws Exception {
        EntityParser<T> concurrentParser = parserPool.take();
        T result = null;
        try {
            result = concurrentParser.parse(xmlToParse);
        } finally {
            parserPool.offer(concurrentParser);
        }
        return result;
    }
}
