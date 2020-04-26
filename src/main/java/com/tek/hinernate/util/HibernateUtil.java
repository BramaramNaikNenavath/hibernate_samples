package com.tek.hinernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static ServiceRegistry serviceRegistry = null;
	private static final SessionFactory sessionFactory = buildSessionFactory();

	public static void main(String[] args) {
		System.out.println(getSessionFactory());
		shutDown();
		System.out.println(sessionFactory);
	}

	private static SessionFactory buildSessionFactory() {
		StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

		serviceRegistry = registryBuilder.configure().build();

		MetadataSources metadataSources = new MetadataSources(serviceRegistry);

		Metadata metadata = metadataSources.getMetadataBuilder().build();

		SessionFactory factory = metadata.buildSessionFactory();

		return factory;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutDown() {
		getSessionFactory().close();
	}
}
