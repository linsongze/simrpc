package org.lsz.simrpc.thransport;

import org.lsz.simrpc.core.RpcRequest;
import org.lsz.simrpc.core.RpcResponse;

/**
 * Created with IntelliJ IDEA.
 * User: lsz
 * Date: 2015/3/13 0013
 * Time: 上午 10:42
 * To change this template use File | Settings | File Templates.
 */
public interface Client {
	public RpcResponse execute(RpcRequest request) throws Exception;
}
