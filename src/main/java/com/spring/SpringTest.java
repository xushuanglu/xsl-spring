package com.spring;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class SpringTest {

	public static void main(String[] args) {
		//调用getBean()时,返回随机数.如果没有指定factory-method,会返回StaticFactoryBean的实例,即返回工厂Bean的实例       
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("config.xml"));       
		System.out.println("我是IT学习者创建的实例:"+factory.getBean("random").toString());
	}
}
