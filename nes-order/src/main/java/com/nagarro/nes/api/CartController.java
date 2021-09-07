package com.nagarro.nes.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.nes.dtos.UpdateCartRequest;
import com.nagarro.nes.exceptions.ApplicationException;
import com.nagarro.nes.models.BaseResponse;
import com.nagarro.nes.models.Cart;
import com.nagarro.nes.services.ICartService;
import com.nagarro.nes.validators.OrderInputValidator;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private ICartService cartService;

	@Autowired
	private OrderInputValidator cartInputValidator;

	private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

	@GetMapping
	public ResponseEntity<BaseResponse<Cart>> getCart(@RequestParam("userID") final String userID)
			throws ApplicationException {
		LOGGER.info("Request received to get cart for user with id:{}", userID);
		return ResponseEntity.ok(new BaseResponse<>(this.cartService.getCartByUserID(userID)));
	}

	@PutMapping
	public ResponseEntity<BaseResponse<Cart>> updatCart(@RequestParam("userID") final String userID,
			@RequestBody @Valid final UpdateCartRequest updateCartRequest, final BindingResult requestBindingResults)
			throws ApplicationException {
		LOGGER.info("Request received to update cart for user with id:{}:{}", userID, updateCartRequest);
		cartInputValidator.checkBindingResults(requestBindingResults);
		return ResponseEntity.ok(new BaseResponse<>(this.cartService.updateCart(updateCartRequest, userID)));
	}

}
