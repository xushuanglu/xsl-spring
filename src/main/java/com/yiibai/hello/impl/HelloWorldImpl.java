package com.yiibai.hello.impl;

import com.yiibai.hello.HelloWorld;

public class HelloWorldImpl implements HelloWorld {

	@Override
	public void printHelloWorld(String msg) {
		System.out.println("Hello : " + msg);
	}

}
