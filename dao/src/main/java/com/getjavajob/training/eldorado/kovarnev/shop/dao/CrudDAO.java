package com.getjavajob.training.eldorado.kovarnev.shop.dao;

import com.getjavajob.training.eldorado.kovarnev.shop.common.BaseEntity;

import java.io.InputStream;
import java.util.List;

public interface CrudDAO<T extends BaseEntity> {

    List<T> getAll(InputStream inputStream) throws DAOException;

}
