package com.nagarro.nes.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.nes.enums.DeliveryState;
import com.nagarro.nes.exceptions.ApplicationException;
import com.nagarro.nes.models.Delivery;
import com.nagarro.nes.services.IDeliveryService;
import com.nagarro.nes.validators.DeliveryInputValidator;

@RestController
@RequestMapping(value = "/delivery")
public class DeliveryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryController.class);

	@Autowired
	private IDeliveryService deliveryService;

	@Autowired
	private DeliveryInputValidator inputValidator;

	@GetMapping
	public ResponseEntity<Delivery> getDeliveryDetailsByOrderID(@RequestParam("orderID") final String orderID)
			throws ApplicationException {
		LOGGER.info("Recieved get Delivery request by order");
		return ResponseEntity.ok(this.deliveryService.getDeliveryDetailsByOrderID(orderID.trim()));
	}

	/**
	 * Update delivery state.
	 *
	 * @param orderID       the order ID
	 * @param deliveryState the delivery state
	 * @return the response entity
	 * @throws ApplicationException the application exception
	 */
	@PutMapping("{orderID}/{deliveryState}")
	public ResponseEntity<Delivery> updateDeliveryState(@PathVariable("orderID") final String orderID,
			@PathVariable("deliveryState") final String deliveryState) throws ApplicationException {
		LOGGER.info("Recieved Update Delivery state request user request");
		inputValidator.validateUpdateDeliveryStateRequest(deliveryState);
		return ResponseEntity
				.ok(this.deliveryService.updateDeliveryState(orderID.trim(), DeliveryState.valueOf(deliveryState)));
	}

}
