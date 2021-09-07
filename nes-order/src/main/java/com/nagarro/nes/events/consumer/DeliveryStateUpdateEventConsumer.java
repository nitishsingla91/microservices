package com.nagarro.nes.events.consumer;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.nagarro.nes.enums.OrderState;
import com.nagarro.nes.events.dtos.DeliveryStateUpdateMessage;
import com.nagarro.nes.models.Order;
import com.nagarro.nes.store.OrderStore;

@Component
public class DeliveryStateUpdateEventConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryStateUpdateEventConsumer.class);

	@RabbitListener(queues = "delivery_state_update_queue")
	public void receivedMessage(final Message<DeliveryStateUpdateMessage> msg) {
		String routingKey = String.valueOf(msg.getHeaders().get(AmqpHeaders.RECEIVED_ROUTING_KEY));
		if (routingKey.equalsIgnoreCase("delivery_state_update")) {
			DeliveryStateUpdateMessage deliveryStateUpdateMessage = msg.getPayload();
			LOGGER.info("Message received for delivery state update:{}", deliveryStateUpdateMessage);
			Optional<Order> requiredOrder = OrderStore.ORDERS_AVAILABLE.stream()
					.filter(order -> order.getOrderID().equalsIgnoreCase(deliveryStateUpdateMessage.getOrderID()))
					.findFirst();
			if (requiredOrder.isPresent()) {
				LOGGER.info("Found order:{}", deliveryStateUpdateMessage.getOrderID());
				requiredOrder.get()
						.setOrderState(OrderState.valueOf(deliveryStateUpdateMessage.getDeliveryState().toString()));
			} else {
				LOGGER.info("Required order is not availble");
			}
		} else {
			LOGGER.info("Invalid routing key:{}", routingKey);
		}

	}

}
