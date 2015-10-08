package org.brodner.alfresco.tomongo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	private static ApplicationContext context;

	public static ApplicationContext getContext() {
		if (context == null) { context = new ClassPathXmlApplicationContext("tomongo-context.xml"); }
		return context;
	}
}
