package com.tek.hibernate.envers;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.hibernate.envers.query.AuditQueryCreator;

import com.tek.beans.Department;
import com.tek.beans.Employee;
import com.tek.hibernate.firstcachemethods.Dept;
import com.tek.hinernate.util.HibernateUtil;

public class HibernateEnversExample {
	public static void main(String[] args) {

		try (SessionFactory factory = HibernateUtil.getSessionFactory();) {
			Session session = factory.openSession();
			AuditReader auditReader = AuditReaderFactory.get(session);
			AuditQueryCreator auditQueryCreator = auditReader.createQuery();
			AuditQuery auditQuery = auditQueryCreator.forRevisionsOfEntity(Dept.class, true, true);

			List<Number> revisions = auditReader.getRevisions(Dept.class, 2);
			revisions.forEach((revision) -> System.out.println(" === " + revision));

			List<Dept> depts = auditQuery.add(AuditEntity.id().eq(1)).getResultList();
			for (int i = 0; i < depts.size(); i++) {
				System.out.println("Revision {} of Post entity: {} " + i + 1 + " " + depts.get(i));
			}

			auditEntities(Employee.class, auditReader);

		}
	}

	private static <T> void auditEntities(Class<T> classObj, AuditReader auditReader) {
		AuditQuery query = auditReader.createQuery().forRevisionsOfEntity(classObj, false, true);
		List<Object[]> resultList = query.getResultList();
		resultList.forEach(objects -> {
			if (Employee.class.equals(classObj)) {
				Employee employeeRev = (Employee) objects[0];
				System.out.println("=== Employee Details === ");
				System.out.println("EMP ID      === " + employeeRev.getId());
				System.out.println("EMP NAME    === " + employeeRev.getName());
				System.out.println("EMP SALARY  === " + employeeRev.getSalary());
				System.out.println("EMP DESG    === " + employeeRev.getDesignation());
				System.out.println("EMP DEPT    === " + employeeRev.getDepartment());
				System.out.println("EMP ADD     === " + employeeRev.getAddress());
			} else if (Department.class.equals(classObj)) {
				System.out.println("=== DEPT Details ===");
				Department department = (Department) objects[0];
				System.out.println("DEPT NO    === " + department.getId());
				System.out.println("D NAME     === " + department.getName());
			}
			DefaultRevisionEntity revisionEntity = (DefaultRevisionEntity) objects[1];
			System.out.println("Revision       : " + revisionEntity.getId());
			System.out.println("Date           : " + revisionEntity.getRevisionDate());
			RevisionType revisionType = (RevisionType) objects[2];
			System.out.println("Operation      : " + revisionType.name());
		});
	}
}
