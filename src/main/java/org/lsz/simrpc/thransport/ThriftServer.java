package org.lsz.simrpc.thransport;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.*;
import org.lsz.simrpc.core.Rpc;
import org.lsz.simrpc.core.RpcService;
import org.lsz.simrpc.handle.RpcServiceHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lsz
 * Date: 2015/3/13 0013
 * Time: 上午 9:25
	服务端
 */
public class ThriftServer implements ApplicationContextAware, InitializingBean {
	private static final Logger log = LoggerFactory.getLogger(ThriftServer.class);
	private RpcService.Iface iface;
	private Map<String, Object> handlerMap = null;
	private int                 port       = 9898;

	public ThriftServer(int port) {
		handlerMap = new HashMap<String, Object>();
		this.iface = new RpcServiceHandle(handlerMap);
		this.port = port;
	}

	public ThriftServer(int port, RpcService.Iface iface) {
		this.iface = iface;
		this.port = port;
	}

	private void start() throws TTransportException {
		//传输通道 - 非阻塞方式
		TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(port);

		//异步IO，需要使用TFramedTransport，它将分块缓存读取。
		TTransportFactory transportFactory = new TFramedTransport.Factory();

		//使用高密度二进制协议
		TProtocolFactory proFactory = new TCompactProtocol.Factory();

		//设置处理器
		TProcessor processor = new RpcService.Processor(iface);

		//创建服务器
		TServer server = new TThreadedSelectorServer(
				new TThreadedSelectorServer.Args(serverTransport)
						.protocolFactory(proFactory)
						.transportFactory(transportFactory)
						.processor(processor)
		);
		log.info("start server in listen "+port);
		server.serve();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(Rpc.class);
		if (serviceBeanMap!=null) {
			for (Object serviceBean : serviceBeanMap.values()) {
				String interfaceName = serviceBean.getClass().getAnnotation(Rpc.class).value().getName();
				handlerMap.put(interfaceName, serviceBean);
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.start();
	}
}
