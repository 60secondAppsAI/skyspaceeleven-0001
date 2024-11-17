package com.skyspaceeleven.service;

import com.skyspaceeleven.dao.GenericDAO;

public interface GenericService<T, ID> {

    abstract GenericDAO<T, ID> getDAO();

    T getById(Integer id) ;

}