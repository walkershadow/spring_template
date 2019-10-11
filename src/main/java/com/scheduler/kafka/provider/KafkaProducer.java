/**
 * @company 杭州信牛网络科技有限公司
 * @copyright Copyright (c) 2015-2017
 */
package com.scheduler.kafka.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

/**
 * kafka生产者
 *
 * @author 陈旭
 * @version $Id: KafkaProducer, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2018年04月12日 下午3:00 陈旭 Exp $
 */
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 将数据推送给kafka队列
     *
     * @param
     * @return
     */
    public void send(String msg) throws ExecutionException, InterruptedException {

        kafkaTemplate.sendDefault(msg).get();

    }

}
