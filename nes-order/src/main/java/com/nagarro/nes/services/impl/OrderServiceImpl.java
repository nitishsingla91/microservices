package com.nagarro.nes.services.impl;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.nagarro.nes.communication.product.client.ProductClient;
import com.nagarro.nes.dtos.ItemDetail;
import com.nagarro.nes.dtos.OrderDO;
import com.nagarro.nes.enums.ErrorCodeType;
import com.nagarro.nes.events.dtos.OrderCreatedEventMessage;
import com.nagarro.nes.events.dtos.OrderFailedEventProductMessage;
import com.nagarro.nes.events.producer.OrderCreatedEventProducer;
import com.nagarro.nes.events.producer.OrderFailedEventProducer;
import com.nagarro.nes.exceptions.ApplicationException;
import com.nagarro.nes.exceptions.DataNotFoundException;
import com.nagarro.nes.exceptions.HttpClientException;
import com.nagarro.nes.exceptions.OrderFailedException;
import com.nagarro.nes.models.Order;
import com.nagarro.nes.services.IOrderService;
import com.nagarro.nes.store.OrderStore;
import com.nagarro.nes.utils.OrderUtils;
import com.netflix.hystrix.exception.HystrixRuntimeException;

@Service
public class OrderServiceImpl implements IOrderService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private ProductClient productClient;

	@Autowired
	private OrderFailedEventProducer orderFailedEventProducer;

	@Autowired
	private OrderCreatedEventProducer orderCreatedEventProducer;

	@Override
	public Order createOrder(@Valid final OrderDO orderRequest) throws ApplicationException {
		this.checkItemAvailable(orderRequest.getItems());
		final Order order = new Order(OrderUtils.generateUniqueOrderID(), orderRequest.getDeliveryAddress(),
				orderRequest.getUserID());
		order.setAmount(orderRequest.getItems().stream().mapToLong(item -> item.getPrice() * item.getQuantity()).sum());
		order.setItems(orderRequest.getItems());
		order.setOrderDate(LocalDateTime.now());
		order.setDelvieryDate(LocalDateTime.now().plusDays(3));
		OrderStore.ORDERS_AVAILABLE.add(order);
		this.orderCreatedEventProducer
				.produceEventOrderCreatedDelivery(new OrderCreatedEventMessage(order.getOrderID()));
		return order;
	}

	private void checkItemAvailable(List<ItemDetail> items) throws OrderFailedException {
		List<OrderFailedEventProductMessage> orderFailedEvents = new ArrayList<>();
		for (ItemDetail item : items) {
			try {
				this.productClient.checkAndRemoveItem(item.getProductID(), item.getQuantity());
				orderFailedEvents.add(new OrderFailedEventProductMessage(item.getQuantity(), item.getProductID()));
			} catch (HttpClientException | HystrixRuntimeException e) {
				this.rollbackProductChange(orderFailedEvents);
				LOGGER.info("Error while interacting with product client:{}", e);
				String message = MessageFormat.format(
						"Order can\''t be placed,no product information of product id {0}", item.getProductID());
				throw new OrderFailedException(message, ErrorCodeType.ORDER_PLACED_FAILED);
			}

		}

	}

	private void rollbackProductChange(List<OrderFailedEventProductMessage> orderFailedEvents) {
		orderFailedEvents.stream()
				.forEach(orderFailedEvent -> this.orderFailedEventProducer.produceEventProductUpdate(orderFailedEvent));
	}

	@Override
	public List<Order> getOrders(final String orderID, final String userID) throws ApplicationException {
		List<Order> orders = null;
		if (StringUtils.isEmpty(orderID) || orderID == null) {
			// no order id , return all orders of given user
			LOGGER.info("order id null, fetching all order of user:{}", userID);
			orders = OrderStore.ORDERS_AVAILABLE.stream().filter(order -> order.getUserId().equalsIgnoreCase(userID))
					.collect(Collectors.toList());
			if (CollectionUtils.isEmpty(orders)) {
				LOGGER.debug("No order present of user:{}", userID);
				throw new DataNotFoundException(ErrorCodeType.USER_ORDER_NOT_FOUND);
			} else {
				LOGGER.info("{} orders found for user:{}", orders.size(), userID);
			}

		} else {
			LOGGER.info("order id not null, fetching only one order of user:{} with order id:{}", userID, orderID);
			// order id in the present , filter only that order
			final Optional<Order> optionalOrder = OrderStore.ORDERS_AVAILABLE.stream().filter(
					order -> order.getUserId().equalsIgnoreCase(userID) && order.getOrderID().equalsIgnoreCase(orderID))
					.findFirst();
			orders = new ArrayList<>();
			orders.add(optionalOrder.orElseThrow(() -> new DataNotFoundException(ErrorCodeType.ORDER_NOT_FOUND)));
		}

		return orders;
	}

}
