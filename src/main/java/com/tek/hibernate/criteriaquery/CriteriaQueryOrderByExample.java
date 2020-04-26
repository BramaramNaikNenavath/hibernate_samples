package com.tek.hibernate.criteriaquery;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tek.beans.Employee;
import com.tek.hinernate.util.HibernateUtil;

public class CriteriaQueryOrderByExample {
	public static void main(String[] args) {
		try (SessionFactory factory = HibernateUtil.getSessionFactory(); Session session = factory.openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			criteriaQuery.select(root);
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get("salary")));
			TypedQuery<Employee> query = session.createQuery(criteriaQuery);
			List<Employee> list = query.getResultList();
			for (Employee employee : list) {
				System.out.println("EMP NAME=" + employee.getName() + "\t SALARY=" + employee.getSalary());
			}
		}
	}
}
