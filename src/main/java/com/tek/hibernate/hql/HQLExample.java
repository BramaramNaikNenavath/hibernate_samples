package com.tek.hibernate.hql;

import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tek.beans.Address;
import com.tek.beans.Employee;
import com.tek.hinernate.util.HibernateUtil;

public class HQLExample {
	public static void main(String[] args) {
		try (SessionFactory sf = HibernateUtil.getSessionFactory(); Session session = sf.openSession()) {
			TypedQuery<Employee> query = session.createQuery("from Employee", Employee.class);
			List<Employee> employees = query.getResultList();
			employees.forEach((employee) -> System.out.println(employee));

			query.setLockMode(LockModeType.READ);

			System.out.println(" === " + query.getLockMode());
			updateRecord(session);

			TypedQuery<Employee> queryRecord = session.createQuery("from Employee where emp_id = :empId",
					Employee.class);
			queryRecord.setParameter("empId", 7);
			System.out.println(queryRecord.getSingleResult());
		}
	}

	private static void updateRecord(Session session) {
		// Update & Delete cannot be typed

		Transaction transaction = session.beginTransaction();
		Query<Employee> query = session
				.createQuery("update Employee set emp_name=:name, salary = :salary where emp_id=:id", Employee.class);
		query.setParameter("name", "Nenavath");
		query.setParameter("id", Long.valueOf(14));
		query.setParameter("salary", 50000);
		int updatedC = query.executeUpdate();
		System.out.println("No of records updated === " + updatedC);
		transaction.commit();

		TypedQuery<Employee> updateQuery = session
				.createQuery("update Employee set emp_name=:name, salary = :salary where emp_id=:id", Employee.class);
		updateQuery.setParameter("name", "Nenavath");
		updateQuery.setParameter("id", Long.valueOf(14));
		updateQuery.setParameter("salary", 50000);
		int updatedCount = updateQuery.executeUpdate();
		System.out.println("No of records updated === " + updatedCount);
	}
}
