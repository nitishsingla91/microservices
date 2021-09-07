package com.nagarro.nes.services;

import com.nagarro.nes.dtos.UpdateCartRequest;
import com.nagarro.nes.exceptions.ApplicationException;
import com.nagarro.nes.models.Cart;

public interface ICartService {

	Cart updateCart(UpdateCartRequest updateCartRequest, String userID) throws ApplicationException;

	Cart getCartByUserID(String userID) throws ApplicationException;

}
