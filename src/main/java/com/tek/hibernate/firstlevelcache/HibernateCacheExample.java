package com.tek.hibernate.firstlevelcache;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tek.beans.Employee;
import com.tek.hinernate.util.HibernateUtil;

public class HibernateCacheExample {
	public static void main(String[] args) throws InterruptedException {
		try (SessionFactory factory = HibernateUtil.getSessionFactory(); Session session = factory.openSession();) {

			Employee emp1 = session.load(Employee.class, Long.valueOf(1));
			System.out.println("=== :: Name = " + emp1.getName() + ", Zipcode =" + emp1.getAddress().getZipcode());

			Thread.sleep(10000);

			Employee emp2 = session.load(Employee.class, Long.valueOf(1));
			System.out.println("=== :: Name = " + emp2.getName() + ", Zipcode =" + emp2.getAddress().getZipcode());

			Employee emp3 = session.load(Employee.class, Long.valueOf(2));
			System.out.println("=== :: Name = " + emp3.getName() + ", Zipcode =" + emp3.getAddress().getZipcode());

			session.evict(emp1);
			System.out.println("=== session emp1 === " + session.contains(emp1));

			Employee emp4 = session.load(Employee.class, Long.valueOf(1));
			System.out.println("=== :: Name = " + emp4.getName() + ", Zipcode =" + emp4.getAddress().getZipcode());

			Employee emp5 = session.load(Employee.class, Long.valueOf(2));
			System.out.println("=== :: Name = " + emp5.getName() + ", Zipcode =" + emp5.getAddress().getZipcode());

			session.clear();
			System.out.println("=== session emp4 === " + session.contains(emp4));
			System.out.println("=== session emp5 === " + session.contains(emp5));

			Employee emp6 = session.load(Employee.class, Long.valueOf(1));
			System.out.println("=== :: Name = " + emp6.getName() + ", Zipcode =" + emp6.getAddress().getZipcode());

			Employee emp7 = session.load(Employee.class, Long.valueOf(2));
			System.out.println("=== :: Name = " + emp7.getName() + ", Zipcode =" + emp7.getAddress().getZipcode());

		}
	}
}
