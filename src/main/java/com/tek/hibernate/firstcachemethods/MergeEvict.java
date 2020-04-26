package com.tek.hibernate.firstcachemethods;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MergeEvict {
	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Dept d = session.load(Dept.class, 20);
		System.out.println(session.contains(d));
		session.evict(d);
		System.out.println(session.contains(d));
		Dept d1 = new Dept();
		d1.setDeptno(90);
		session.merge(d);
		System.out.println(session.contains(d));
		transaction.commit();
		session.close();
		factory.close();
	}
}
