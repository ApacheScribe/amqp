package com.ascribe.amqp.util;

import static com.ascribe.amqp.config.QConfig.channel;

import java.io.IOException;

import com.ascribe.amqp.config.QConfig;
import com.rabbitmq.client.DeliverCallback;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Hello world: RabbitMQ
 * 
 */

@Component
public class Basic {

    private static final Logger log = Logger.getLogger(Basic.class);

    @Value("${QUEUE-NAME}")
    private String queueName;

    @Value("${QUEUE-HOST}")
    private String host;

    private static QConfig q;

    @Autowired
    public Basic(QConfig q) {
        Basic.q = q;
    }

    public Boolean send(String message) throws IOException {
        Boolean sent = false;
        System.out.println("");
        q.connect(queueName, host);
        channel.basicPublish("", queueName, null, message.getBytes());
        log.info("[x] Sent: [" + message + "] to queue [" + queueName + "]");
        sent = true;
        return sent;
    }

    public String receive() throws IOException {
        String msg = null;
        q.connect(queueName, host);
        DeliverCallback dc = (consumerTag, delivery) -> {
            final String message = new String(delivery.getBody(), "UTF-8");
            log.info("[x] Received: [" + message + "] from queue [" + queueName + "]");
        };
        msg = channel.basicConsume(queueName, true, dc, consumerTag -> {
        });
        return msg;
    }
}