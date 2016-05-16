package com.getjavajob.training.eldorado.kovarnev.shop.service;

import com.getjavajob.training.eldorado.kovarnev.shop.common.Customer;
import com.getjavajob.training.eldorado.kovarnev.shop.common.Order;
import com.getjavajob.training.eldorado.kovarnev.shop.common.Position;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.CrudDAO;
import com.getjavajob.training.eldorado.kovarnev.shop.dao.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class CustomerService implements CrudService<Customer> {

    private CrudDAO<Customer> crudDAO;

    @Autowired
    public CustomerService(CrudDAO<Customer> crudDAO) {
        this.crudDAO = crudDAO;
    }

    @Override
    public List<Customer> getAll(InputStream inputStream) throws ServiceException {
        List<Customer> customers;
        try {
            customers = crudDAO.getAll(inputStream);
        } catch (DAOException e) {
            throw new ServiceException("CustomerService Service getAll", e);
        }
        return customers;
    }

    @Override
    public CalculationModule calculateCustomersInfo(List<Customer> customers) {
        CalculationModule holder = new CalculationModule();
        for (Customer customer : customers) {
            double ordersPriceSum = 0;
            List<Order> orders = customer.getOrders();
            for (int i = 0; i < orders.size(); i++) {
                double orderSum = 0;
                List<Position> positions = orders.get(i).getPositions();
                for (int j = 0; j < positions.size(); j++) {
                    orderSum += positions.get(j).getPrice() * positions.get(j).getCount();
                    if (j == (positions.size() - 1)) {
                        ordersPriceSum += orderSum;
                        holder.getOrdersPrice().add(orderSum);
                        if (i == (orders.size() - 1)) {
                            holder.getCustomersAndOrdersSum().put(customer, ordersPriceSum);
                        }
                    }
                }
            }
        }
        return holder;
    }
}
