package com.nagarro.nes.events.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.nes.events.dtos.DeliveryStateUpdateMessage;

@Component
public class DeliveryStateUpdatedProducer {

	@Autowired
	private AmqpTemplate rabbitmqTemplate;

	@Autowired
	private MessageConverter messageConvertor;

	private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryStateUpdatedProducer.class);

	public void produceEventProductUpdate(final DeliveryStateUpdateMessage message) {
		LOGGER.info("Delivery State change ,sending event to order :{}", message);
		rabbitmqTemplate.convertAndSend("nes_order_exchange", "delivery_state_update",
				messageConvertor.toMessage(message, new MessageProperties()));
	}
}
