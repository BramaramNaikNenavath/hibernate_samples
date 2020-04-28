package com.tek.hibernate.firstcachemethods;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.tek.beans.Employee;

public class HibernateUpdateExample {
	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();

		//Employee d = session.load(Employee.class, Long.valueOf(9));
		//d.setName("VINAY");
		
		Dept d = session.load(Dept.class, 4);
		d.setDname("Dummy1_Academy");
		
		session.update(d);
		transaction.commit();
		session.close();
		factory.close();
	}
}
