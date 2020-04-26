package com.tek.hibernate.criteriaquery;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tek.beans.Employee;
import com.tek.hinernate.util.HibernateUtil;

public class CriteriaQueryAggregateExample {
	public static void main(String[] args) {
		try (SessionFactory factory = HibernateUtil.getSessionFactory(); Session session = factory.openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

			// Count number of employees
			CriteriaQuery<Long> criteriaCountQuery = criteriaBuilder.createQuery(Long.class);
			Root<Employee> rootCount = criteriaCountQuery.from(Employee.class);
			criteriaCountQuery.select(criteriaBuilder.count(rootCount));
			Long count = session.createQuery(criteriaCountQuery).getSingleResult();
			System.out.println("=== Total No of records === " + count);

			// Get max min salary
			CriteriaQuery<Double> criteriaMaxMinQuery = criteriaBuilder.createQuery(Double.class);
			Root<Employee> rootMaxMin = criteriaMaxMinQuery.from(Employee.class);
			criteriaMaxMinQuery.select(criteriaBuilder.max(rootMaxMin.get("salary")));
			Double maxSalary = session.createQuery(criteriaMaxMinQuery).getSingleResult();

			criteriaMaxMinQuery.select(criteriaBuilder.min(rootMaxMin.get("salary")));
			Double minSalary = session.createQuery(criteriaMaxMinQuery).getSingleResult();

			System.out.println("=== Maximum Salary === " + maxSalary);
			System.out.println("=== Minimum Salary === " + minSalary);

			// Get Average Salary
			CriteriaQuery<Double> criteriaAvgQuery = criteriaBuilder.createQuery(Double.class);
			Root<Employee> rootAvg = criteriaAvgQuery.from(Employee.class);
			criteriaAvgQuery.select(criteriaBuilder.avg(rootAvg.get("salary")));
			Double avgSal = session.createQuery(criteriaAvgQuery).getSingleResult();
			System.out.println("=== Average Salary === " + avgSal);

			// Count distinct employees
			CriteriaQuery<Long> criteriaDistinctQuery = criteriaBuilder.createQuery(Long.class);
			Root<Employee> distinctRoot = criteriaDistinctQuery.from(Employee.class);
			criteriaDistinctQuery.multiselect(criteriaBuilder.countDistinct(distinctRoot.get("name")));
			Long distinctCount = session.createQuery(criteriaDistinctQuery).getSingleResult();
			System.out.println("=== Distinct Name count === " + distinctCount);
		}
	}
}
