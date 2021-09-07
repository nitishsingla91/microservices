package com.nagarro.nes.configuratiion;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMQConfig {

	@Bean
	public Queue productUpdateQueue() {
		return new Queue("product_update_queue", true);
	}

	@Bean
	public Queue orderCreatedQueue() {
		return new Queue("order_created_queue", true);
	}

	@Bean
	DirectExchange productExchange() {
		return new DirectExchange("nes_product_exchange");
	}

	@Bean
	DirectExchange deliveryExchange() {
		return new DirectExchange("nes_delivery_exchange");
	}

	@Bean
	Binding bindingProductUpdateMessages() {
		return BindingBuilder.bind(productUpdateQueue()).to(productExchange()).with("product_update");
	}

	@Bean
	Binding bindingOrderCreatedMessges() {
		return BindingBuilder.bind(orderCreatedQueue()).to(deliveryExchange()).with("order_created");
	}

	@Bean
	public Queue deliveryStateUpdateQueue() {
		return new Queue("delivery_state_update_queue", true);
	}

	@Bean
	Binding bindingMessages() {
		return BindingBuilder.bind(deliveryStateUpdateQueue()).to(orderExchange()).with("delivery_state_update");
	}

	@Bean
	public Queue orderPaidQueue() {
		return new Queue("order_paid_queue", true);
	}

	@Bean
	DirectExchange orderExchange() {
		return new DirectExchange("nes_order_exchange");
	}

	@Bean
	Binding bindingOrderPaidMessages() {
		return BindingBuilder.bind(orderPaidQueue()).to(orderExchange()).with("order_paid");
	}

	@Bean
	public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}

	@Bean
	public RabbitTemplate amqpTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter());
		return rabbitTemplate;
	}

	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter(new ObjectMapper());
	}

}
