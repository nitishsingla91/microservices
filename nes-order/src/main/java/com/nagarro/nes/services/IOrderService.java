package com.nagarro.nes.services;

import java.util.List;

import com.nagarro.nes.dtos.OrderDO;
import com.nagarro.nes.exceptions.ApplicationException;
import com.nagarro.nes.models.Order;

public interface IOrderService {

	Order createOrder(OrderDO order) throws ApplicationException;

	List<Order> getOrders(String orderID, final String userID) throws ApplicationException;

}
