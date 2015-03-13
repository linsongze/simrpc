package org.lsz.simrpc.core;

/**
 * Created with IntelliJ IDEA.
 * User: lsz
 * Date: 2015/3/13 0013
 * Time: 上午 10:46
 * To change this template use File | Settings | File Templates.
 */
public class InvokeRequest {
	private String className;
	private String methodName;
	private Class<?>[] parameterTypes;
	private Object[] parameters;
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}
}
