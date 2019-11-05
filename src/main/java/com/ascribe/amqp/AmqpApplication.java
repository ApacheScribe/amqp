package com.ascribe.amqp;

import com.ascribe.amqp.logic.Logic;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AmqpApplication {

	private static final Logger log = Logger.getLogger(AmqpApplication.class);

	private static Logic logic;

	public AmqpApplication(Logic logic) {
		AmqpApplication.logic = logic;
	}

	public static void main(String[] args) {
		SpringApplication.run(AmqpApplication.class, args);
		log.info("[Name] application started;");
		logic.run();
	}
}
