package com.nagarro.nes.events.consumer;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.nagarro.nes.events.dtos.OrderFailedEventProductMessage;
import com.nagarro.nes.models.Product;
import com.nagarro.nes.store.ProductStore;

@Component
public class ProductUpdateEventConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductUpdateEventConsumer.class);

	@RabbitListener(queues = "product_update_queue")
	public void receivedMessage(final Message<OrderFailedEventProductMessage> msg) {
		LOGGER.info("Message received for order failed:{}", msg.getPayload());
		String routingKey = String.valueOf(msg.getHeaders().get(AmqpHeaders.RECEIVED_ROUTING_KEY));
		if (routingKey.equalsIgnoreCase("product_update")) {
			OrderFailedEventProductMessage orderFailedEventProductMessage = msg.getPayload();
			Optional<Product> requiredProduct = ProductStore.PRODUCTS_AVAILABLE.stream().filter(
					product -> product.getProductID().equalsIgnoreCase(orderFailedEventProductMessage.getProductID()))
					.findFirst();
			if (requiredProduct.isPresent()) {
				LOGGER.info("Found product:{}", orderFailedEventProductMessage.getProductID());
				requiredProduct.get().setQuantity(
						requiredProduct.get().getQuantity() + orderFailedEventProductMessage.getQuantity());
			} else {
				LOGGER.info("Required product is not availble");
			}
		} else {
			LOGGER.info("Invalid routing key:{}", routingKey);
		}

	}

}
