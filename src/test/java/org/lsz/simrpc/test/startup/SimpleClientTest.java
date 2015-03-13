package org.lsz.simrpc.test.startup;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.lsz.simrpc.core.Rpc;
import org.lsz.simrpc.core.RpcRequest;
import org.lsz.simrpc.core.RpcResponse;
import org.lsz.simrpc.core.RpcService;

import java.nio.ByteBuffer;

/**
 * Created with IntelliJ IDEA.
 * User: lsz
 * Date: 2015/3/13 0013
 * Time: 上午 10:16
 * thrift 代码
 */
public class SimpleClientTest {
	public static void main(String[] args) throws TException {
		//设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
		TTransport transport = new TFramedTransport(new TSocket("localhost", 9898));
		transport.open();

		//使用高密度二进制协议
		TProtocol protocol = new TCompactProtocol(transport);

		RpcRequest request = new RpcRequest(1, ByteBuffer.wrap(new byte[]{1,3,4,5}));
		//创建Client
		RpcService.Client client = new RpcService.Client(protocol);
		RpcResponse response =  client.execute(request);
		System.out.println(response);
	}
}
