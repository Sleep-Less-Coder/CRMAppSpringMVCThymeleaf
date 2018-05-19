package com.hemant.springmvc.crm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hemant.springmvc.crm.entity.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getListOfCustomers() {
		Session currentSession = getSessionFactory().getCurrentSession();
		Query query = currentSession.createQuery("FROM Customer");
		List<Customer> list = query.list();
		return list;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void saveCustomer(Customer customer) {
		getSessionFactory().getCurrentSession().saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int customerId) {
		Session currentSession = getSessionFactory().getCurrentSession();

		Customer customer = currentSession.get(Customer.class, customerId);

		return customer;
	}

	@Override
	public void deleteCustomer(int customerId) {
		Session currentSession = getSessionFactory().getCurrentSession();

		Query query = currentSession.createQuery("delete from Customer where id = :customerId");
		query.setParameter("customerId", customerId);

		query.executeUpdate();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Customer> searchCustomers(String searchName) {
		Session currentSession = getSessionFactory().getCurrentSession();

		Query query = null;

		if (searchName != null && searchName.trim().length() > 0) {

			query = currentSession
					.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName");

			query.setParameter("theName", "%" + searchName.toLowerCase() + "%");

		} else {
			query = currentSession.createQuery("from Customer");
		}

		List<Customer> customers = query.list();

		return customers;
	}

}
