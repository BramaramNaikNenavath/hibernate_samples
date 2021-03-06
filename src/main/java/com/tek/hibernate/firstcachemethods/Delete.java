package com.tek.hibernate.firstcachemethods;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Delete {
	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Dept d = session.load(Dept.class, 4);
		
		//Dept d = new Dept();
		//d.setDeptno(20);
		session.delete(d);
		transaction.commit();
		session.close();
		factory.close();
	}
}
