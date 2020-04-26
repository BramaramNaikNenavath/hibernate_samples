package com.tek.hibernate.criteriaquery;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tek.beans.Department;
import com.tek.beans.Employee;
import com.tek.hinernate.util.HibernateUtil;

public class CriteriaQueryGroupHavingExample {
	public static void main(String[] args) {
		try (SessionFactory factory = HibernateUtil.getSessionFactory(); Session session = factory.openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			Root<Employee> root = criteriaQuery.from(Employee.class);

			criteriaQuery.groupBy(root.get("salary"), root.get("department"));
			criteriaQuery.having(criteriaBuilder.greaterThan(root.get("salary"), 30000));
			TypedQuery<Object[]> query = session.createQuery(criteriaQuery);
			List<Object[]> list = query.getResultList();
			System.out.println(list.size());
			for (Object[] objects : list) {
				System.out.println(objects[0] + " " + objects[1]);
				// System.out.println(employee + " | " + department);
			}
		}
	}
}
