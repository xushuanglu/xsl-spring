Spring自动装配Beans 

在Spring框架，可以用 auto-wiring 功能会自动装配Bean。要启用它，只需要在 <bean>定义“autowire”属性。
	<bean id="customer" class="com.yiibai.common.Customer" autowire="byName" />

在Spring中，支持 5 自动装配模式。

    1、no – 缺省情况下，自动配置是通过“ref”属性手动设定
    	 1. Auto-Wiring ‘no’
		这是默认的模式，你需要通过 'ref' 属性来连接 bean。
		<bean id="customer" class="com.yiibai.common.Customer">
		         <property name="person" ref="person" />
		</bean>
		<bean id="person" class="com.yiibai.common.Person" />
    2、byName – 根据属性名称自动装配。如果一个bean的名称和其他bean属性的名称是一样的，将会自装配它。
    	 2. Auto-Wiring ‘byName’
		按属性名称自动装配。在这种情况下，由于对“person” bean的名称是相同于“customer” bean 的属性(“person”)名称，所以，Spring会自动通过setter方法将其装配 – “setPerson(Person person)“.
		<bean id="customer" class="com.yiibai.common.Customer" autowire="byName" />	
		<bean id="person" class="com.yiibai.common.Person" />
		
    3、byType – 按数据类型自动装配。如果一个bean的数据类型是用其它bean属性的数据类型，兼容并自动装配它。
    	 3. Auto-Wiring ‘byType’
		通过按属性的数据类型自动装配Bean。在这种情况下，由于“Person” bean中的数据类型是与“customer” bean的属性(Person对象)的数据类型一样的，所以，Spring会自动通过setter方法将其自动装配。– “setPerson(Person person)“.
		<bean id="customer" class="com.yiibai.common.Customer" autowire="byType" />
		<bean id="person" class="com.yiibai.common.Person" />
    4、constructor – 在构造函数参数的byType方式。
    	 4. Auto-Wiring ‘constructor’
		通过构造函数参数的数据类型按属性自动装配Bean。在这种情况下，由于“person” bean的数据类型与“customer” bean的属性(Person对象)的构造函数参数的数据类型是一样的，所以，Spring通过构造方法自动装配 – “public Customer(Person person)“.
		<bean id="customer" class="com.yiibai.common.Customer" autowire="constructor" />
		<bean id="person" class="com.yiibai.common.Person" />
    5、autodetect – 如果找到默认的构造函数，使用“自动装配用构造”; 否则，使用“按类型自动装配”。



从Spring 3起，JavaConfig功能已经包含在Spring核心模块，它允许开发者将bean定义和在Spring配置XML文件到Java类中。
但是，仍然允许使用经典的XML方式来定义bean和配置，JavaConfig是另一种替代解决方案。
看来看经典的XML定义和JavaConfig的不同，如下定义在Spring容器中的bean。

Spring XML file - applicationContext.xml :

	<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">
	 
		<bean id="helloBean" class="com.yiibai.hello.impl.HelloWorldImpl">
			
	</beans>

等效于以下JavaConfig的配置：

	package com.yiibai.config;
	
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import com.yiibai.hello.HelloWorld;
	import com.yiibai.hello.impl.HelloWorldImpl;
	
	@Configuration
	public class AppConfig {
	    @Bean(name="helloBean")
	    public HelloWorld helloWorld() {
	        return new HelloWorldImpl();
	    }
	}    	


一般来说, 需要按模块或类别 分割Spring XML bean文件 成多个小文件, 使事情更容易维护和模块化。 例如，

<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans-2.5.xsd">
 
	<import resource="config/customer.xml"/>
        <import resource="config/scheduler.xml"/>
	</beans>

Spring3 JavaConfig它等效于 @Import 功能

package com.yiibai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ CustomerConfig.class, SchedulerConfig.class })
public class AppConfig {

}



在Spring框架中，依赖注入(DI)的设计模式是用来定义对象彼此间的依赖。它主要有两种类型：
	 1、Setter方法注入
	 	一个 bean 配置文件用来声明bean 和通过 setter 设置注入(property标签)的依赖。
	 	
	<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans-2.5.xsd">

	<bean id="OutputHelper" class="com.yiibai.output.OutputHelper">
		<property name="outputGenerator">
			<ref bean="CsvOutputGenerator" />
		</property>
	</bean>
	
	<bean id="CsvOutputGenerator" class="com.yiibai.output.impl.CsvOutputGenerator" />
	<bean id="JsonOutputGenerator" class="com.yiibai.output.impl.JsonOutputGenerator" />
		
	</beans>
	只需注入一个“CsvOutputGenerator” bean 到 “OutputHelper”对象，通过一个 setter 方法(setOutputGenerator)。 
	 2、构造器注入
	  bean 配置文件来声明bean并通过构造函数(constructor-arg标签)设置注入依赖。
		<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans-2.5.xsd">
		
			<bean id="OutputHelper" class="com.yiibai.output.OutputHelper">
				<constructor-arg>
					<bean class="com.yiibai.output.impl.CsvOutputGenerator" />
				</constructor-arg>
			</bean>
			
		<bean id="CsvOutputGenerator" class="com.yiibai.output.impl.CsvOutputGenerator" />
		<bean id="JsonOutputGenerator" class="com.yiibai.output.impl.JsonOutputGenerator" />
				
		</beans>

		只需通过一个构造函数注入一个 “CsvOutputGenerator” Bean 到 “OutputHelper” 对象。 
		

	 


