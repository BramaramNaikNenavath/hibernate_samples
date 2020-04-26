package com.tek.hibernate.firstcachemethods;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Persist {
	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();

		Transaction transaction = session.beginTransaction();

		Dept d = new Dept();
		// d.setDeptno(22);
		d.setDname("ABC");
		d.setLoc("ABC");

		System.out.println("-----------");
		session.persist(d);
		transaction.commit();
		System.out.println("-----------");

		session.close();
		factory.close();
	}
}
