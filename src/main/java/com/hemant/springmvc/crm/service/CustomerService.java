package com.hemant.springmvc.crm.service;

import java.util.List;

import com.hemant.springmvc.crm.entity.Customer;

public interface CustomerService {
	public List<Customer> getListOfCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int customerId);

	public void deleteCustomer(int customerId);

	public List<Customer> searchCustomers(String searchName);
}
