package com.cy.common.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Yjie
 */
@Component
public class SpringUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	private SpringUtil() {
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringUtil.applicationContext == null) {
			SpringUtil.applicationContext = applicationContext;
		}
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}

	public static <T> T getBean(Class<T> requiredType) {
		return getApplicationContext().getBean(requiredType);
	}

	public static <T> T getBean(String name, Class<T> requiredType) {
		return getApplicationContext().getBean(name, requiredType);
	}
}
