package com.tek.hibernate.envers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.envers.configuration.EnversSettings;

import com.tek.beans.Address;
import com.tek.beans.Department;
import com.tek.beans.Employee;

public class SaveEmployeeForAuditing {
	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		
		Session session = factory.openSession();

		saveEmployee(session);

		session.close();
		factory.close();
	}

	public static void saveEmployee(Session session) {
		Transaction transaction = session.beginTransaction();

		// Department department1 = new Department();
		// department1.setId(4);
		// department1.setName("Software Delivery");

		Department department1 = session.load(Department.class, Long.valueOf(2));

		Employee emp = new Employee();
		Address add = new Address();
		emp.setName("Jagadeesh");
		emp.setDesignation("Senior Technical Lead");
		emp.setSalary(180000);
		add.setAddressLine1("Hitech");
		add.setCity("HYD");
		add.setZipcode("500042");
		emp.setAddress(add);
		add.setEmployee(emp);
		emp.setDepartment(department1);

		department1.getEmployees().add(emp);

		// session.save(department1);
		session.save(emp);

		transaction.commit();
	}
}
