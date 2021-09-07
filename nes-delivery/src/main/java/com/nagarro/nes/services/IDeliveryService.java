package com.nagarro.nes.services;

import com.nagarro.nes.enums.DeliveryState;
import com.nagarro.nes.exceptions.DeliveryNotFoundException;
import com.nagarro.nes.models.Delivery;

public interface IDeliveryService {

	Delivery updateDeliveryState(String orderID, DeliveryState deliveryStatus) throws DeliveryNotFoundException;

	Delivery getDeliveryDetailsByDeliveryID(String deliveryID) throws DeliveryNotFoundException;

	Delivery getDeliveryDetailsByOrderID(String orderID) throws DeliveryNotFoundException;

}
