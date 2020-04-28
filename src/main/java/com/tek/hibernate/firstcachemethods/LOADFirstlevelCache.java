package com.tek.hibernate.firstcachemethods;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class LOADFirstlevelCache {
	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();

		Transaction transaction = session.beginTransaction();

		Dept d = session.load(Dept.class, 20);
		System.out.println("=== " + d.getDeptno() + " ===");
		System.out.println(d);

		// System.out.println(session.get(Dept.class, 20).getDname());
		// System.out.println("------------");
		// session.evict(d); // removed from FIRST_LEVEL_CACHE hence queried again
		// Dept d1 = session.load(Dept.class, 20);
		// session.clear();
		// System.out.println("=== " + d1.getDname() + " ==="); // cached from
		// FIRST_LEVEL_CACHE

		transaction.commit();
		session.disconnect();
		factory.close();
	}
}
