package com.tek.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSample {
	public static void main(String[] args) {
		System.out.println("------------------------------------------------");
		Configuration configuration = new Configuration();
		configuration.configure();
		System.out.println(configuration.getProperties());
		System.out.println("------------------------------------------------");
		SessionFactory factory = configuration.buildSessionFactory();
		System.out.println("------------------------------------------------");
		Session session = factory.openSession();
		System.out.println("------------------------------------------------");
		System.out.println(session);
	}
}
