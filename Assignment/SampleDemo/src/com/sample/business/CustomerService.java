package com.sample.business;

import java.util.List;
import java.util.Map;

import com.sample.exception.CustomerExceptionClass;
import com.sample.model.Customer;

public interface CustomerService {

	void saveOrUpdateCustomer(Map<String, Object> inputJson) throws CustomerExceptionClass;

	List<Customer> getCustomerData(Map<String, Object> inputJson) throws CustomerExceptionClass;

}

