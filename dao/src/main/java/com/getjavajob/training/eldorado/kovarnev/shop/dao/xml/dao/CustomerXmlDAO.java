package com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.dao;

import com.getjavajob.training.eldorado.kovarnev.shop.common.Customer;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.CrudDAO;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.DAOException;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.ParseException;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.xml.parser.concurrent.ConcurrentParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;

@Repository
public class CustomerXmlDAO implements CrudDAO<Customer> {

    private ConcurrentParser<Customer> concurrentParser;

    @Autowired
    public CustomerXmlDAO(ConcurrentParser<Customer> concurrentParser) {
        this.concurrentParser = concurrentParser;
    }

    @Override
    public List<Customer> getAll(InputStream inputStream) throws DAOException {
        List<Customer> customers;
        try {
            customers = concurrentParser.parseList(inputStream);
        } catch (ParseException e) {
            throw new DAOException("CustomerXmlDAO getAll exception", e);
        }
        return customers;
    }

}
