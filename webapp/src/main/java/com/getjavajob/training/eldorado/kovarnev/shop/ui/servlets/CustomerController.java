package com.getjavajob.training.eldorado.kovarnev.shop.ui.servlets;

import com.getjavajob.training.eldorado.kovarnev.shop.common.Customer;
import com.getjavajob.training.eldorado.kovarnev.shop.service.CalculationModule;
import com.getjavajob.training.eldorado.kovarnev.shop.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {

    @Autowired
    private CrudService<Customer> customerService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String xmlForm() {
        return "xmlForm";
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public String index(@RequestParam("xml") MultipartFile xml,
                        @RequestParam(value = "sumFilter", defaultValue = "0") double sumFilter, Model model) {
        InputStream inputStream = null;
        try {
            inputStream = xml.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Customer> customers = customerService.getAll(inputStream);
        CalculationModule holder = customerService.calculateCustomersInfo(customers);
        List<Customer> customerList = holder.getFilteredByOrdersSumCustomers(sumFilter);
        Map.Entry<Customer, Double> biggestCustomer = holder.getCustomerWithBiggestOrderPrice();
        if (biggestCustomer != null) {
            String biggestCustomersName = biggestCustomer.getKey().getName();
            Double biggestCustomersNameOrdersSum = biggestCustomer.getValue();
            model.addAttribute("biggestCustomersName", biggestCustomersName);
            model.addAttribute("biggestCustomersOrdersSum", biggestCustomersNameOrdersSum);
        }
        model.addAttribute("maxOrdersPrice", holder.getMaxOrderPrice());
        model.addAttribute("minOrdersPrice", holder.getMinOrderPrice());
        model.addAttribute("ordersTotalAmount", holder.getSumOfAllOrders());
        model.addAttribute("ordersQty", holder.getTotalOrdersCount());
        model.addAttribute("averageOrdersPrice", holder.getAvgOrdersPrice());
        model.addAttribute("customerList", customerList);
        model.addAttribute("sumFilter", sumFilter);

        return "xmlForm";
    }
}
