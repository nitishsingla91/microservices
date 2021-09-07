package com.nagarro.nes.services;

import java.util.List;

import com.nagarro.nes.dto.UpdateProductQuantityRequest;
import com.nagarro.nes.exception.ApplicationException;
import com.nagarro.nes.models.Product;

/**
 * The Interface IProductService.
 *
 * @author chetanmahajan
 */
public interface IProductService {

	/**
	 * Gets the all products by search text. if no text then return all products
	 *
	 * @param productSearchText the product search text
	 * @return the all products by search text
	 */
	List<Product> getAllProductsBySearchText(final String productSearchText);

	/**
	 * Gets the product by id.
	 *
	 * @param productID the product ID
	 * @return the product by id
	 * @throws ApplicationException the product application exception
	 */
	Product getProductById(final String productID) throws ApplicationException;

	/**
	 * Update quantity.
	 *
	 * @param productID
	 *
	 * @param request   the request
	 */
	Product updateQuantity(String productID, UpdateProductQuantityRequest request) throws ApplicationException;

	/**
	 * Adds the products.
	 *
	 * @param products the products
	 * @throws ApplicationException
	 */
	List<Product> addProducts(List<Product> products) throws ApplicationException;

	/**
	 * Delete products.
	 *
	 * @param productId the product id
	 */
	List<Product> deleteProducts(List<String> productId);

}
