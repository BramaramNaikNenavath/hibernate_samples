package com.tek.hibernate.criteriaquery;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tek.beans.Employee;
import com.tek.hinernate.util.HibernateUtil;

public class CriteriaQueryExample {
	public static void main(String[] args) {
		try (SessionFactory factory = HibernateUtil.getSessionFactory(); Session session = factory.openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);

			Root<Employee> e = query.from(Employee.class);
			// select * from employee;
			query.select(e);
			TypedQuery<Employee> tQuery = session.createQuery(query);
			tQuery.getResultList().forEach((emp) -> System.out.println(emp));

			query.select(e).where(criteriaBuilder.equal(e.get("id"), 7L));
			// select * from department where id = 7;
			TypedQuery<Employee> tyQuery = session.createQuery(query);
			Employee emp = tyQuery.getSingleResult();
			System.out.println(emp);

			// select name, salary from employee;
			CriteriaQuery<Object[]> queryMul = criteriaBuilder.createQuery(Object[].class);
			Root<Employee> em = queryMul.from(Employee.class);
			queryMul.multiselect(em.get("name"), em.get("salary"));
			TypedQuery<Object[]> tyMuQuery = session.createQuery(queryMul);
			tyMuQuery.getResultList()
					.forEach((empl) -> System.out.println(" Name == " + empl[0] + " | Salary == " + empl[1]));
		}
	}
}
