package com.tek.hibernate.firstcachemethods;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Get {
	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();

		Dept d = session.get(Dept.class, 20);
		System.out.println(d.getDname());
		System.out.println("-----------");
		Dept d1 = session.get(Dept.class, 20);
		System.out.println(d.getDname());

		session.close();
		factory.close();
	}
}
