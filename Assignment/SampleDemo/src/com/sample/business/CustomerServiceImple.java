package com.sample.business;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.sample.dao.GenericDAO;
import com.sample.exception.CustomerExceptionClass;
import com.sample.jsonvalidator.JsonValidator;
import com.sample.model.Customer;

@Component
public class CustomerServiceImple implements CustomerService{

	@SuppressWarnings("unchecked")
	@Autowired
	private GenericDAO genericDAO;

	@SuppressWarnings("unchecked")
	@Autowired
	private JsonValidator jsonValidator;

	@SuppressWarnings("unchecked")
	@Override
	public void saveOrUpdateCustomer(Map<String, Object> inputJson) throws CustomerExceptionClass{
		Customer customer = null;
		try {
			for(String parentKey : inputJson.keySet()){
				Map<String,Object> data = (Map<String, Object>)inputJson.get(parentKey);
				for(String key : data.keySet()){
					customer = (Customer) jsonValidator.validateJson((Map<String, Object>)data.get(key), Customer.class);
					genericDAO.saveOrUpdate(customer);
				}
			}
		} catch (CustomerExceptionClass e) {
			throw new CustomerExceptionClass(e.getCause().getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getCustomerData(Map<String, Object> inputJson) throws CustomerExceptionClass{
		Integer customerId = null;
		String exam = null;
		List<String> examList = null;
		List<Customer> customers = null;
		try {
			for(String key : inputJson.keySet()){
				if("customerId".equalsIgnoreCase(key)){
					customerId = (Integer)inputJson.get(key);
				}
				if("exam".equalsIgnoreCase(key)){
					exam = (String)inputJson.get(key);
					examList = Arrays.asList(exam.split(","));
				}
			}

			if(customerId != null && !CollectionUtils.isEmpty(examList)){
				StringBuilder exams = new StringBuilder();
                exams.append("(");
                for (String value : examList) {
                    exams.append("'" + value + "'" + ",");
                }
                exams.delete(exams.lastIndexOf(","), exams.length());
                exams.append(")");
				customers = (List<Customer>)genericDAO.callHQLQuery(String.format("SELECT cust FROM Customer cust "
						+ "WHERE cust.examInstanceId IN %s AND cust.customerId = '%d'", exams, customerId));
			} 
		}catch (CustomerExceptionClass e) {
			throw new CustomerExceptionClass(e.getCause().getMessage(), e);
		}
		return customers;
	}
}
