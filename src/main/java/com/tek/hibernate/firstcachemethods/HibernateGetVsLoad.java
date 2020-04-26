package com.tek.hibernate.firstcachemethods;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tek.beans.Employee;

public class HibernateGetVsLoad {
	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();

		Employee emp = (Employee) session.get(Employee.class, Long.valueOf(2));
		System.out.println("=== Employee get called ===");
		System.out.println("=== Employee ID= " + emp.getId() + " ===");
		System.out.println("=== Employee Get Details:: " + emp + " ===");
		System.out.println("--------------");
		Employee emp1 = (Employee) session.load(Employee.class, Long.valueOf(1));
		System.out.println("=== Employee load called ===");
		System.out.println("=== Employee ID= " + emp1.getId() + " ===");
		System.out.println("=== Employee load Details:: " + emp1 + " ===");

		session.disconnect();
		factory.close();
	}
}
