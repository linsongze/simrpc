package org.lsz.simrpc.test.startup;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RpcServerBootstrap {
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("spring-server.xml");
	}
}
