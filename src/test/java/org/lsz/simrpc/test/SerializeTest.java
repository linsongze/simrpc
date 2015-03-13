package org.lsz.simrpc.test;

import org.lsz.simrpc.utils.KryoKit;

/**
 * Created with IntelliJ IDEA.
 * User: lsz
 * Date: 2015/3/13 0013
 * Time: 上午 11:29
 * To change this template use File | Settings | File Templates.
 */
public class SerializeTest {
	public static void main(String[] args) {
		String sss = "xxx";
		byte[] bs = KryoKit.serialize(sss);
		String s = KryoKit.deserialize(bs,String.class);
		System.out.println(s);
	}
}
