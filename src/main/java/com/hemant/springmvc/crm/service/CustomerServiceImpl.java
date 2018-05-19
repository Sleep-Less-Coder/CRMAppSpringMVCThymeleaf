package com.hemant.springmvc.crm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemant.springmvc.crm.dao.CustomerDao;
import com.hemant.springmvc.crm.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDao customerDao;
	
	@Override
	@Transactional
	public List<Customer> getListOfCustomers() {
		return getCustomerDao().getListOfCustomers();
	}
	
	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		getCustomerDao().saveCustomer(customer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int customerId) {
		return getCustomerDao().getCustomer(customerId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int customerId) {
		getCustomerDao().deleteCustomer(customerId);
	}

	/*
	 * 
	 * Getter and Setter methods for customerDao
	 */
	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	@Transactional
	public List<Customer> searchCustomers(String searchName) {
		return getCustomerDao().searchCustomers(searchName);
	}
}
