package com.tek.hibernate.nativequery;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tek.beans.Employee;
import com.tek.hinernate.util.HibernateUtil;

public class NativeQueryExample {
	public static void main(String[] args) {
		try (SessionFactory factory = HibernateUtil.getSessionFactory(); Session session = factory.openSession()) {

			List<Employee> employees = session.createNativeQuery("select * from employee", Employee.class).list();
			employees.forEach((emp) -> System.out.println(emp));
		}
	}
}
