package com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.concurrent;

import com.getjavajob.training.eldorado.kovarnev.shop.common.BaseEntity;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.ParseException;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.entity.EntityParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Component
public abstract class AbstractConcurrentParser<T extends BaseEntity> implements ConcurrentParser<T> {

    private static final int DEFAULT_XML_POOL_CAPACITY = Runtime.getRuntime().availableProcessors();
    protected SAXParserFactory spf;
    private BlockingQueue<EntityParser<T>> parserPool;
    private ExecutorService executorService;
    private XMLInputFactory xif;
    private TransformerFactory tf;

    @Autowired
    public AbstractConcurrentParser(XMLInputFactory xif, TransformerFactory tf, SAXParserFactory spf) {
        this.spf = spf;
        parserPool = new ArrayBlockingQueue<>(DEFAULT_XML_POOL_CAPACITY);
        for (int i = 0; i < DEFAULT_XML_POOL_CAPACITY; i++) {
            parserPool.add(getEntityParser());
        }
        executorService = Executors.newFixedThreadPool(DEFAULT_XML_POOL_CAPACITY);
        this.xif = xif;
        this.tf = tf;
    }

    @Override
    public List<T> parseList(InputStream is) throws ParseException {
        XMLStreamReader xsr;
        try {
            xsr = xif.createXMLStreamReader(is);
        } catch (XMLStreamException e) {
            throw new ParseException("parseList exception", e);
        }
        try {
            xsr.nextTag(); // Advance to statements element
        } catch (XMLStreamException e) {
            throw new ParseException("parseList exception", e);
        }
        Transformer t;
        try {
            t = tf.newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new ParseException("parseList exception", e);
        }
        List<Future<T>> futures = new ArrayList<>();
        try {
            while (xsr.nextTag() == XMLStreamConstants.START_ELEMENT) {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                t.transform(new StAXSource(xsr), new StreamResult(os));
                ByteArrayInputStream chunkToParse = new ByteArrayInputStream(os.toByteArray());
                ParseTask<T> parseTask = new ParseTask<>(chunkToParse, parserPool);
                Future<T> future = executorService.submit(parseTask);
                futures.add(future);
            }
        } catch (XMLStreamException | TransformerException e) {
            throw new ParseException("parseList exception", e);
        }
        List<T> result = new ArrayList<>();
        for (Future<T> future : futures) {
            try {
                result.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new ParseException("parseList exception", e);
            }
        }
        return result;
    }
}
