package com.tek.hibernate.firstcachemethods;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.tek.beans.Employee;

public class HibernateMergeExample {
	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Employee emp = (Employee) session.load(Employee.class, Long.valueOf(13));
		System.out.println(emp);

		emp.getAddress().setAddressLine1("Updated Address");

		session.merge(emp);

		transaction.commit();
		session.close();
		factory.close();
	}
}
