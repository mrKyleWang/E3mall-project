package cn.e3mall.activemq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageConsumer {
	
	public void msgConsumer() throws Exception {
		//初始化Spring容器
		ApplicationContext applicationContext =new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq");
		//等待
		System.in.read();
	
	}
}
