package com.nagarro.nes.events.consumer;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.nagarro.nes.enums.OrderState;
import com.nagarro.nes.events.dtos.OrderFailedEventProductMessage;
import com.nagarro.nes.events.dtos.OrderPaidEventMessage;
import com.nagarro.nes.events.dtos.PaymentStatus;
import com.nagarro.nes.events.producer.OrderFailedEventProducer;
import com.nagarro.nes.models.Cart;
import com.nagarro.nes.models.Order;
import com.nagarro.nes.store.CartStore;
import com.nagarro.nes.store.OrderStore;

@Component
public class OrderPaidEventConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderPaidEventConsumer.class);

	@Autowired
	private OrderFailedEventProducer orderFailedEventProducer;

	@RabbitListener(queues = "order_paid_queue")
	public void receivedMessage(final Message<OrderPaidEventMessage> msg) {
		String routingKey = String.valueOf(msg.getHeaders().get(AmqpHeaders.RECEIVED_ROUTING_KEY));
		if (routingKey.equalsIgnoreCase("order_paid")) {
			OrderPaidEventMessage orderPaidMessage = msg.getPayload();
			LOGGER.info("Message received for payment:{}", orderPaidMessage);
			Optional<Order> requiredOrder = OrderStore.ORDERS_AVAILABLE.stream()
					.filter(order -> order.getOrderID().equalsIgnoreCase(orderPaidMessage.getOrderID())).findFirst();
			if (requiredOrder.isPresent()) {
				LOGGER.info("Found order:{}", orderPaidMessage.getOrderID());
				if (orderPaidMessage.getPaymentStatus() == PaymentStatus.PAYMENT_FAILED) {
					LOGGER.info("Payment has been failed for order id:{}", orderPaidMessage.getOrderID());
					requiredOrder.get().setOrderState(OrderState.FAILED);
					// generate event to product to update product quantity
					requiredOrder.get().getItems()
							.forEach(item -> this.orderFailedEventProducer.produceEventProductUpdate(
									new OrderFailedEventProductMessage(item.getQuantity(), item.getProductID())));
				} else {
					LOGGER.info("Payment has been done for order id:{}", orderPaidMessage.getOrderID());
					LOGGER.debug("Remove cart now");
					Optional<Cart> cartToBeRemoved = CartStore.CARTS_AVAILABLE.stream()
							.filter(cart -> cart.getUserID().equalsIgnoreCase(requiredOrder.get().getUserId()))
							.findFirst();
					if (cartToBeRemoved.isPresent()) {
						CartStore.CARTS_AVAILABLE.remove(cartToBeRemoved.get());
					} else {
						LOGGER.debug("Cart not found for user with user ID:{}", requiredOrder.get().getUserId());
					}
					requiredOrder.get().setOrderState(OrderState.APPROVED);
				}

			} else {
				LOGGER.info("Required order is not availble");
			}
		} else {
			LOGGER.info("Invalid routing key:{}", routingKey);
		}

	}

}
