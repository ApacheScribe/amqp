package com.ascribe.amqp.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class QConfig {

    private static final Logger log = Logger.getLogger(QConfig.class);

    public static Channel channel;

    public void connect(String queueName, String host) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setUsername("admin");
        factory.setPassword("admin");
        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(queueName, true, false, false, null);
        } catch (Exception e) {
            log.error(e);
        }
    }
}