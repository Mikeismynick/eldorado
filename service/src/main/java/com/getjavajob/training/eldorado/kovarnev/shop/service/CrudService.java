package com.getjavajob.training.eldorado.kovarnev.shop.service;

import com.getjavajob.training.eldorado.kovarnev.shop.common.BaseEntity;

import java.io.InputStream;
import java.util.List;

public interface CrudService<T extends BaseEntity> {

    List<T> getAll(InputStream inputStream) throws ServiceException;

    CalculationModule calculateCustomersInfo(List<T> entity);
}
