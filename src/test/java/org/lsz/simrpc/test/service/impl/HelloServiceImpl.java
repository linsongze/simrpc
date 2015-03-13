package org.lsz.simrpc.test.service.impl;

import org.lsz.simrpc.core.Rpc;
import org.lsz.simrpc.test.service.HelloService;

/**
 * Created with IntelliJ IDEA.
 * User: lsz
 * Date: 2015/3/13 0013
 * Time: 上午 10:03
 * To change this template use File | Settings | File Templates.
 */

@Rpc(HelloService.class)
public class HelloServiceImpl implements HelloService {
	@Override
	public String echo(String req) {
		return req+"1000";
	}
}
