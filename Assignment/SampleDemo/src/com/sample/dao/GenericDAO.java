package com.sample.dao;

import java.io.Serializable;
import java.util.List;

import com.sample.exception.CustomerExceptionClass;

public interface GenericDAO<T , P extends Serializable> {

	void saveOrUpdate(T object) throws CustomerExceptionClass;

	List<T> callHQLQuery(String hqlQuery) throws CustomerExceptionClass;
}

