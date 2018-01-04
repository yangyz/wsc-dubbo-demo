/** 基于Dubbo的分布式系统架构视频教程，吴水成，wu-sc@foxmail.com，学习交流QQ群：367211134 **/
package wusc.edu.demo.mqtest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import wusc.edu.demo.mqtest.params.MailParam;

/**
 * 
 * @描述: ActiveMQ测试启动类 .
 * @作者: WuShuicheng .
 * @创建时间: 2015-3-17,上午2:25:20 .
 * @版本号: V1.0 .
 */
public class MQProducerTestA {
	private static final Log log = LogFactory.getLog(MQProducerTestA.class);

	public static void main(String[] args) throws InterruptedException {
//		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					"classpath:spring/spring-context.xml");
			context.start();

			MQProducer mqProducer = (MQProducer) context.getBean("mqProducer");

			int num = 10000000;
			for (int i = 0; i < num; i++) {
				log.info("==>生产者A:send message:"+i);
				// 邮件发送
				MailParam mail = new MailParam();
				mail.setTo("生产者A:" + i + ":wu-sc@foxmail.com");
				mail.setSubject("生产者A:" + i + ":ActiveMQ测试");
				mail.setContent("生产者A:" + i + ":通过ActiveMQ异步发送邮件！");
				mqProducer.sendMessage(mail);
				Thread.sleep(500);
			}

//			context.stop();
//		} catch (Exception e) {
//			log.error("==>MQ context start error:", e);
//			System.exit(0);
//		} finally {
//			log.info("===>System.exit");
//			System.exit(0);
//		}
	}
}
