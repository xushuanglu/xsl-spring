package com.yiibai.import_;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Import实例 
 * @author Administrator
 */
public class App {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		CustomerBo customer = (CustomerBo) context.getBean("customer");
		customer.printMsg("Hello 11");

		SchedulerBo scheduler = (SchedulerBo) context.getBean("scheduler");
		scheduler.printMsg("Hello 22");

	}
}
