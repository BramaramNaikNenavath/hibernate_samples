package com.tek.hibernate.interceptor;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.tek.hibernate.firstcachemethods.Dept;

public class LoggingInterceptor extends EmptyInterceptor {
	
	private static Logger logger = LogManager.getLogger(LoggingInterceptor.class);
	
	public LoggingInterceptor() {
		logger.info(" === LoggingInterceptor Object Created === ");
	}

	private static final long serialVersionUID = -182099440158178092L;

	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		logger.info("==== onLoad Interceptor method invoked === ");
		Dept d = (Dept) entity;
		logger.info(d);
		logger.info(Arrays.asList(propertyNames));
		logger.info(Arrays.asList(types));
		return true;
	}
	
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		logger.info("==== onFlushDirty Interceptor method invoked === ");
		return true;
	}
}
