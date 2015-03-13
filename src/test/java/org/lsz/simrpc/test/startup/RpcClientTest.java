package org.lsz.simrpc.test.startup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lsz.simrpc.handle.proxy.RpcServiceProxy;
import org.lsz.simrpc.test.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: lsz
 * Date: 2015/3/13 0013
 * Time: 上午 11:54
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-client.xml")
public class RpcClientTest {
	@Autowired
	private RpcServiceProxy rpcServiceProxy;
	@Test
	public void helloService(){
		HelloService helloService = rpcServiceProxy.create(HelloService.class);
		System.out.println(helloService.echo("xxx"));
	}
}
