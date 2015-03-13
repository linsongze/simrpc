package org.lsz.simrpc.handle;

import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;
import org.apache.thrift.TException;
import org.lsz.simrpc.core.*;
import org.lsz.simrpc.utils.KryoKit;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lsz
 * Date: 2015/3/13 0013
 * Time: 上午 9:13
 * To change this template use File | Settings | File Templates.
 */
public class RpcServiceHandle implements RpcService.Iface {
	private Map<String, Object> handlerMap;
	public RpcServiceHandle(Map<String, Object> handlerMap){
		this.handlerMap = handlerMap;
	}
	@Override
	public RpcResponse execute(RpcRequest request) throws TException {
		long id = request.getRid();
		byte[] body = request.getBody();

		InvokeResponse invokeResponse = new InvokeResponse();
		try {
			InvokeRequest invokeRequest = KryoKit.deserialize(body, InvokeRequest.class);
			invoke(invokeRequest,invokeResponse);
		}catch (Throwable t){
			invokeResponse.setError(t);
		}

		RpcResponse response = new RpcResponse();
		response.setRid(id);
		response.setBody(KryoKit.serialize(invokeResponse)) ;
		return response;
	}
	private void invoke(InvokeRequest request,InvokeResponse response) throws InvocationTargetException {
		String className = request.getClassName();
		Object serviceBean = handlerMap.get(className);

		Class<?> serviceClass = serviceBean.getClass();
		String methodName = request.getMethodName();
		Class<?>[] parameterTypes = request.getParameterTypes();
		Object[] parameters = request.getParameters();

		/*
		 * Method method = serviceClass.getMethod(methodName, parameterTypes);
		 * method.setAccessible(true); return method.invoke(serviceBean,
		 * parameters);
		 */

		FastClass serviceFastClass = FastClass.create(serviceClass);
		FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, parameterTypes);
		response.setResult( serviceFastMethod.invoke(serviceBean, parameters));
	}
}
