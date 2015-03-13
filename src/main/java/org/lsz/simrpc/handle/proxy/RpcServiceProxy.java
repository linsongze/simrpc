package org.lsz.simrpc.handle.proxy;

import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;
import org.lsz.simrpc.core.InvokeRequest;
import org.lsz.simrpc.core.InvokeResponse;
import org.lsz.simrpc.core.RpcRequest;
import org.lsz.simrpc.core.RpcResponse;
import org.lsz.simrpc.thransport.Client;
import org.lsz.simrpc.utils.KryoKit;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: lsz
 * Date: 2015/3/13 0013
 * Time: 上午 10:41
	为接口统一生成代理调用方法
 */
public class RpcServiceProxy {
	private Client client;
	public RpcServiceProxy(Client client){
		this.client = client;
	}
	@SuppressWarnings("unchecked")
	public <T> T create(Class<?> interfaceClass) {
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass},
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						InvokeRequest request = new InvokeRequest();
						request.setClassName(method.getDeclaringClass().getName());
						request.setMethodName(method.getName());
						request.setParameterTypes(method.getParameterTypes());
						request.setParameters(args);

						RpcRequest rpcRequest = new RpcRequest(new Date().getTime()+ new Random().nextLong(),ByteBuffer.wrap(KryoKit.serialize(request)));

						RpcResponse rpcResponse = client.execute(rpcRequest);

						InvokeResponse response = KryoKit.deserialize(rpcResponse.getBody(),InvokeResponse.class);

						if(response.isError()){
							return response.getError();
						}else {
							return response.getResult();
						}

					}
				});
	}
}

