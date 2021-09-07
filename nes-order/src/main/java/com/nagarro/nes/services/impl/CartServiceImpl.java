package com.nagarro.nes.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nagarro.nes.dtos.UpdateCartRequest;
import com.nagarro.nes.enums.ErrorCodeType;
import com.nagarro.nes.enums.UpdateCartProductType;
import com.nagarro.nes.exceptions.CartUpdateFailedException;
import com.nagarro.nes.exceptions.DataNotFoundException;
import com.nagarro.nes.models.Cart;
import com.nagarro.nes.models.CartDetails;
import com.nagarro.nes.services.ICartService;
import com.nagarro.nes.store.CartStore;

@Service
public class CartServiceImpl implements ICartService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

	@Override
	public Cart updateCart(final UpdateCartRequest updateCartRequest, final String userID)
			throws CartUpdateFailedException {

		Cart retResult = null;
		final Optional<Cart> optionalRequiredCart = CartStore.CARTS_AVAILABLE.stream()
				.filter(cart -> cart.getUserID().equalsIgnoreCase(userID)).findFirst();

		// cart present increase quantity by one
		if (optionalRequiredCart.isPresent()) {
			LOGGER.info("Cart is present of user:{}:{}", userID, optionalRequiredCart.get());
			final Optional<CartDetails> optionaCartDetails = optionalRequiredCart.get().getCartDetails().stream()
					.filter(cartDetail -> cartDetail.getProductID().equalsIgnoreCase(updateCartRequest.getProductID()))
					.findFirst();
			// product not already there add it
			if (!optionaCartDetails.isPresent() && updateCartRequest.getUpdateCartType() == UpdateCartProductType.INC) {
				LOGGER.info("Cart is present but product is not there,so adding it:{}",
						updateCartRequest.getProductID());
				optionalRequiredCart.get().getCartDetails().add(new CartDetails(updateCartRequest.getProductID()));
			} else if (!optionaCartDetails.isPresent()
					&& updateCartRequest.getUpdateCartType() == UpdateCartProductType.DEC) {
				throw new CartUpdateFailedException("Can't decrease product not in cart",
						ErrorCodeType.CART_UPDATE_FAILED);
			} else {
				LOGGER.info("Cart and product is present increase quantity");
				// product already in cart increase quantity by one
				if (updateCartRequest.getUpdateCartType() == UpdateCartProductType.INC) {
					optionaCartDetails.get().setQuantity(optionaCartDetails.get().getQuantity() + 1);
				} else {
					optionaCartDetails.get().setQuantity(optionaCartDetails.get().getQuantity() - 1);
				}
			}
			retResult = optionalRequiredCart.get();

		} else if (!optionalRequiredCart.isPresent()
				&& updateCartRequest.getUpdateCartType() == UpdateCartProductType.INC) {
			LOGGER.info("Cart is not present of user:{}", userID);
			// cart not availble for given user , create new
			final Cart cart = new Cart(userID);
			final List<CartDetails> cartItemList = new ArrayList<>();
			cartItemList.add(new CartDetails(updateCartRequest.getProductID()));
			cart.setCartDetails(cartItemList);
			retResult = cart;
			CartStore.CARTS_AVAILABLE.add(cart);
		} else {
			throw new CartUpdateFailedException("Can't decrease product not in cart", ErrorCodeType.CART_UPDATE_FAILED);
		}

		return retResult;

	}

	@Override
	public Cart getCartByUserID(final String userID) throws DataNotFoundException {
		final Optional<Cart> optionalRequiredCart = CartStore.CARTS_AVAILABLE.stream()
				.filter(cart -> cart.getUserID().equalsIgnoreCase(userID)).findFirst();
		return optionalRequiredCart.orElseThrow(() -> new DataNotFoundException(ErrorCodeType.CART_NOT_FOUND));
	}

}
