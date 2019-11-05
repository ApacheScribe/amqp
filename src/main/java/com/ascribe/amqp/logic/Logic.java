package com.ascribe.amqp.logic;

import com.ascribe.amqp.util.Basic;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Logic {

    private static final Logger log = Logger.getLogger(Logic.class);

    private static Basic basic;

    public Logic(Basic basic) {
        Logic.basic = basic;
    }

    public void run() {
        try {
            basic.send("Hello World");
            String received = basic.receive();
            log.info("Received message: " + received);
        } catch (Exception e) {
            log.error(e);
        }
    }
}