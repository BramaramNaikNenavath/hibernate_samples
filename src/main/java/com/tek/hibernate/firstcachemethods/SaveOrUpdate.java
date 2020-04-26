package com.tek.hibernate.firstcachemethods;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SaveOrUpdate {
	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();

		Transaction transaction = session.beginTransaction();

		Dept d = new Dept();
		// d.setDeptno(8);
		d.setDname("EDUCATION");
		d.setLoc("SWEDEN");

		System.out.println("-----------");
		session.saveOrUpdate(d);
		transaction.commit();
		System.out.println("-----------");

		session.close();
		factory.close();
	}
}
