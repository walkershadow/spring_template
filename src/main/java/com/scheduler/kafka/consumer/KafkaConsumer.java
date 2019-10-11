package com.scheduler.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

/**
 * kafka消费者
 *
 * @author 陈旭
 * @version $Id: KafkaConsumer, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2018年04月12日 下午3:00 陈旭 Exp $
 */
public class KafkaConsumer implements MessageListener<String, String> {

    @Override
    public void onMessage(ConsumerRecord<String, String> consumerRecord) {
        String receiveMessage = consumerRecord.value();
        System.out.println("消息： " + receiveMessage);

    }

}
