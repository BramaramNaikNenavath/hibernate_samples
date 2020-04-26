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

public class CriteriaQueryFromJoinExample {
	public static void main(String[] args) {
		try (SessionFactory factory = HibernateUtil.getSessionFactory(); Session session = factory.openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

			CriteriaQuery<Object[]> criteriaFromJoinQuery = criteriaBuilder.createQuery(Object[].class);
			Root<Employee> rootEmp = criteriaFromJoinQuery.from(Employee.class);
			Root<Department> rootDep = criteriaFromJoinQuery.from(Department.class);

			criteriaFromJoinQuery.multiselect(rootEmp, rootDep);
			criteriaFromJoinQuery.where(criteriaBuilder.equal(rootEmp.get("department"), rootDep.get("id")));

			// select * from employee as e cross join department as d where e.dept_id = d.dept_id;

			TypedQuery<Object[]> query = session.createQuery(criteriaFromJoinQuery);
			List<Object[]> list = query.getResultList();
			System.out.println(list.size());
			for (Object[] objects : list) {
				Employee employee = (Employee) objects[0];
				Department department = (Department) objects[1];
				System.out.println(employee + " | "+department);
			}

			// same we can achieve using native SQL Query but maintainence issue when we switch to other DB

			TypedQuery<Employee> qq = session.createNativeQuery("select * from Employee where dept_id = :id", Employee.class);
			qq.setParameter("id", 5);
			List<Employee> list1 = qq.getResultList();
			System.out.println("============== " + list1.size());
			list1.forEach((emp) -> System.out.println(emp + " | "+emp.getDepartment()));
		}
	}
}
