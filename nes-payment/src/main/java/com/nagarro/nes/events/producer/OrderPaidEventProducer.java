package com.nagarro.nes.events.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.nes.events.dtos.OrderPaidEventMessage;

@Component
public class OrderPaidEventProducer {

	@Autowired
	private AmqpTemplate rabbitmqTemplate;

	@Autowired
	private MessageConverter messageConvertor;

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderPaidEventProducer.class);

	public void produceEventProductUpdate(final OrderPaidEventMessage message) {
		LOGGER.info("Order paid producing event for order :{}", message);
		rabbitmqTemplate.convertAndSend("nes_order_exchange", "order_paid",
				messageConvertor.toMessage(message, new MessageProperties()));
	}

}
