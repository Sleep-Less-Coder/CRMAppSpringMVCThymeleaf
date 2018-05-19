package com.hemant.springmvc.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hemant.springmvc.crm.entity.Customer;
import com.hemant.springmvc.crm.service.CustomerService;

@Controller
@RequestMapping("/")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/")
	public String showHome(Model model) {
		List<Customer> customers = customerService.getListOfCustomers();
		model.addAttribute("customers", customers);
		return "home";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "customer-form";
	}
	
	@PostMapping("saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		System.out.println(customer);
		getCustomerService().saveCustomer(customer);
		return "redirect:/";
	}
	
	@GetMapping("showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int customerId, Model model) {
		
		Customer customer = getCustomerService().getCustomer(customerId);
		
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
	@GetMapping("delete")
	public String deleteCustomer(@RequestParam("customerId") int customerId) {
		
		getCustomerService().deleteCustomer(customerId);
		
		return "redirect:/";
	}
	
	@PostMapping("search")
	public String searchCustomers(@RequestParam("searchName") String searchName, Model model) {
        List<Customer> customers = customerService.searchCustomers(searchName);
                
        model.addAttribute("customers", customers);

        return "home"; 
	}
	/*
	 * Getter and Setter for customer service
	 */
	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
}
