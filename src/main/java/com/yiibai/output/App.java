package com.yiibai.output;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "Spring-Common.xml" });

		OutputHelper output = (OutputHelper) context.getBean("OutputHelper");
		output.generateOutput();
	}
}
