package com.tek.hibernate.firstcachemethods;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.tek.beans.Address;
import com.tek.beans.Department;
import com.tek.beans.Employee;

public class HibernateSaveExample {
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

		Department department1 = new Department();
		department1.setId(1);
		department1.setName("App_Dev_Practice");

		Department department2 = new Department();
		department2.setId(2);
		department2.setName("Software_Development");

		Department department3 = new Department();
		department3.setId(3);
		department3.setName("Human_Resources");

		Employee emp = new Employee();
		Address add = new Address();
		emp.setName("Nenavath");
		emp.setDesignation("Module Lead");
		emp.setSalary(125000);
		add.setAddressLine1("Hitech");
		add.setCity("HYD");
		add.setZipcode("500042");
		emp.setAddress(add);
		add.setEmployee(emp);
		emp.setDepartment(department1);

		Employee emp1 = new Employee();
		Address add1 = new Address();
		emp1.setName("Naveen");
		emp1.setSalary(160000);
		emp1.setDesignation("Senior Lead");
		add1.setAddressLine1("Hitech");
		add1.setCity("HYD");
		add1.setZipcode("500042");
		emp1.setAddress(add1);
		add1.setEmployee(emp1);
		emp1.setDepartment(department1);

		Employee emp2 = new Employee();
		Address add2 = new Address();
		emp2.setName("Abhishek");
		emp2.setDesignation("CEO");
		emp2.setSalary(175000);
		add2.setAddressLine1("Hitech");
		add2.setCity("HYD");
		add2.setZipcode("500042");
		emp2.setAddress(add2);
		add2.setEmployee(emp2);
		emp2.setDepartment(department1);

		Employee emp3 = new Employee();
		Address add3 = new Address();
		emp3.setName("Abhishek");
		emp3.setDesignation("Technical Lead");
		emp3.setSalary(175000);
		add3.setAddressLine1("Hitech");
		add3.setCity("HYD");
		add3.setZipcode("500042");
		emp3.setAddress(add3);
		add3.setEmployee(emp3);
		emp3.setDepartment(department1);

		Employee emp4 = new Employee();
		Address add4 = new Address();
		emp4.setName("Vaishnavi");
		emp4.setDesignation("Software Engineer");
		emp4.setSalary(30000);
		add4.setAddressLine1("GREENPARK");
		add4.setCity("Banglore");
		add4.setZipcode("500092");
		emp4.setAddress(add4);
		add4.setEmployee(emp4);
		emp4.setDepartment(department1);

		department1.getEmployees().add(emp);
		department1.getEmployees().add(emp1);
		department1.getEmployees().add(emp2);
		department1.getEmployees().add(emp3);
		department1.getEmployees().add(emp4);

		session.save(department1);
		transaction.commit();
	}

	public static Employee getTestEmployee() {
		Employee emp = new Employee();
		Address add = new Address();
		emp.setName("Test Emp");
		emp.setSalary(1000);
		add.setAddressLine1("Test address1");
		add.setCity("Test City");
		add.setZipcode("12121");
		emp.setAddress(add);
		add.setEmployee(emp);
		return emp;
	}
}
