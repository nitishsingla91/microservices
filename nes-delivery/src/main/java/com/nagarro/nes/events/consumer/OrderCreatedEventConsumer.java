package com.nagarro.nes.events.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.nagarro.nes.enums.DeliveryState;
import com.nagarro.nes.events.dtos.OrderCreatedEventMessage;
import com.nagarro.nes.models.Delivery;
import com.nagarro.nes.store.DeliveryStore;
import com.nagarro.nes.utils.DeliveryUtils;

@Component
public class OrderCreatedEventConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderCreatedEventConsumer.class);

	@RabbitListener(queues = "order_created_queue")
	public void receivedMessage(final Message<OrderCreatedEventMessage> msg) {
		String routingKey = String.valueOf(msg.getHeaders().get(AmqpHeaders.RECEIVED_ROUTING_KEY));
		if (routingKey.equalsIgnoreCase("order_created")) {
			OrderCreatedEventMessage orderCreatedMessage = msg.getPayload();
			LOGGER.info("Message received for order delivery:{}", orderCreatedMessage);
			DeliveryStore.DELIVERIES_AVAILABLE.add(new Delivery(orderCreatedMessage.getOrderId(),
					DeliveryState.PACKAGING_IN_PROGRESS, DeliveryUtils.generateUniqueDeliveryID()));
		} else {
			LOGGER.info("Invalid routing key:{}", routingKey);
		}
	}
}