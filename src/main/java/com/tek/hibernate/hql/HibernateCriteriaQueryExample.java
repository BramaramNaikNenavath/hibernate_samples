package com.tek.hibernate.hql;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tek.hibernate.firstcachemethods.Dept;

public class HibernateCriteriaQueryExample {
	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();

		getAllDepts(session);
		searchByLoc(session);
		searchByDeptNo(session);
		multiLoad(session);

		session.close();
		factory.close();
	}

	private static void getAllDepts(Session session) {
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Dept> query = cb.createQuery(Dept.class);
		Root<Dept> root = query.from(Dept.class);
		CriteriaQuery<Dept> all = query.select(root);

		TypedQuery<Dept> allQuery = session.createQuery(all);
		List<Dept> depts = allQuery.getResultList();

		System.out.println(" === " + depts.size());
		depts.forEach((dept) -> System.out.println(
				"DEPT Record is == [" + dept.getDeptno() + ", " + dept.getDname() + ", " + dept.getLoc() + "]"));
	}

	private static void searchByLoc(Session session) {
		String searchKey = "INDIA";
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Dept> query = cb.createQuery(Dept.class);

		Root<Dept> root = query.from(Dept.class);
		CriteriaQuery<Dept> de = query.select(root).where(cb.like(root.get("loc"), searchKey));

		List<Dept> st = session.createQuery(de).getResultList();
		System.out.println("=== SIZE === " + st.size());
		st.forEach((loc) -> System.out.println("LOC is == " + loc.getLoc()));
	}

	private static void searchByDeptNo(Session session) {
		int deptno = 20;
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Dept> query = cb.createQuery(Dept.class);

		Root<Dept> root = query.from(Dept.class);
		CriteriaQuery<Dept> de = query.select(root).where(cb.equal(root.get("deptno"), deptno));

		List<Dept> st = session.createQuery(de).getResultList();
		System.out.println("=== SIZE === " + st.size());
		st.forEach((loc) -> System.out.println("DEPT NO is == " + loc.getDeptno()));
	}

	private static void multiLoad(Session session) {
		MultiIdentifierLoadAccess<Dept> depts = session.byMultipleIds(Dept.class);
		List<Dept> list = depts.multiLoad(7, 8, 9, 10);
		list.forEach((dept) -> System.out.println("MULTILOAD DEPT Record is == [" + dept.getDeptno() + ", "
				+ dept.getDname() + ", " + dept.getLoc() + "]"));
	}

}
