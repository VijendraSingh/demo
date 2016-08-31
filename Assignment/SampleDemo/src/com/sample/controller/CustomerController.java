package com.sample.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.business.CustomerService;
import com.sample.exception.CustomerExceptionClass;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/saveOrUpdateCustomer", method = RequestMethod.POST)
	public final Map<String, Object> saveOrUpdateCustomer(@RequestBody final Map<String, Object> reportJson) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			//Call business layer
			customerService.saveOrUpdateCustomer(reportJson);
			result.put("status", "success");
			result.put("result", "Successfully Saved..!!");
		} catch (CustomerExceptionClass e) {
			result.put("status", "fail");
			result.put("result", e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/getCustomerData")
    public final Map<String, Object> getReports(@RequestBody final Map<String, Object> inputJson) {
		Map<String, Object> result = new HashMap<String, Object>();
        try {
        	// Call business layer
        	result.put("result", customerService.getCustomerData(inputJson));
        	result.put("status", "success");
		} catch (CustomerExceptionClass e) {
			result.put("status", "fail");
			result.put("result", e.getMessage());
		}
		return result;
    }
}
