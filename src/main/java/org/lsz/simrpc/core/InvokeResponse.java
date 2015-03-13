package org.lsz.simrpc.core;

/**
 * Created with IntelliJ IDEA.
 * User: lsz
 * Date: 2015/3/13 0013
 * Time: 上午 10:47
 * To change this template use File | Settings | File Templates.
 */
public class InvokeResponse {
	private Throwable error;
	private Object result;
	public boolean isError(){
		return error != null;
	}

	public Throwable getError() {
		return error;
	}

	public void setError(Throwable error) {
		this.error = error;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
