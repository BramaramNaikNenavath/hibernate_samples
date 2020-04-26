package com.tek.hibernate.firstcachemethods;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Save {
	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();

		Transaction transaction = session.beginTransaction();

		Dept d = new Dept();
		// d.setDeptno(19);
		d.setDname("MATERIALS");
		d.setLoc("CHINA");

		System.out.println("-----------");
		Serializable generatedId = session.save(d);
		System.out.println("=== GENERATED ID === " + generatedId);
		transaction.commit();
		System.out.println("-----------");

		session.close();
		factory.close();
	}
}
