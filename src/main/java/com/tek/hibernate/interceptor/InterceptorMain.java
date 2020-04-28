package com.tek.hibernate.interceptor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.tek.hibernate.firstcachemethods.Dept;

public class InterceptorMain {
	public static void main(String[] args) {
		Configuration configuration = new Configuration().setInterceptor(new LoggingInterceptor());
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		
		
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Dept d = session.load(Dept.class, 20);
		System.out.println("=== " + d.getDeptno() + " ===");
		System.out.println(d);

		session.close();
		factory.close();
	}
}
