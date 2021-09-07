package com.nagarro.nes.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.nagarro.nes.dto.UpdateProductQuantityRequest;
import com.nagarro.nes.enums.ErrorCodeType;
import com.nagarro.nes.enums.UpdateQuantityType;
import com.nagarro.nes.exception.ApplicationException;
import com.nagarro.nes.exception.ProductNotFoundException;
import com.nagarro.nes.exception.ProductUpdateFailedException;
import com.nagarro.nes.models.Product;
import com.nagarro.nes.services.IProductService;
import com.nagarro.nes.store.ProductStore;

/**
 * The Class ProductServiceImpl.
 *
 * @author chetanmahajan
 */
@Service
public class ProductServiceImpl implements IProductService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Override
	public List<Product> getAllProductsBySearchText(final String productSearchText) {
		LOGGER.info("No. of products availble in store:{}", ProductStore.PRODUCTS_AVAILABLE.size());
		Predicate<? super Product> filterPredicate = null;
		List<Product> retResult = null;
		if (!StringUtils.isEmpty(productSearchText) && productSearchText != null) {
			// filter by category,name and description
			filterPredicate = product -> product.getCategory().toLowerCase().contains(productSearchText.toLowerCase())
					|| product.getDescription().toLowerCase().contains(productSearchText.toLowerCase())
					|| product.getProductName().toLowerCase().contains(productSearchText.toLowerCase());
			retResult = ProductStore.PRODUCTS_AVAILABLE.stream().filter(filterPredicate).collect(Collectors.toList());
		} else {
			retResult = ProductStore.PRODUCTS_AVAILABLE;
		}
		return retResult;
	}

	@Override
	public Product getProductById(final String productID) throws ApplicationException {
		final Optional<Product> optionalProduct = ProductStore.PRODUCTS_AVAILABLE.stream()
				.filter(product -> product.getProductID().equalsIgnoreCase(productID)).findAny();
		return optionalProduct.orElseThrow(() -> new ProductNotFoundException("Product with requested ID not found",
				ErrorCodeType.PRODUCT_NOT_FOUND));
	}

	@Override
	public Product updateQuantity(final String productID, final UpdateProductQuantityRequest request)
			throws ApplicationException {
		final Optional<Product> optionalRequiredProduct = ProductStore.PRODUCTS_AVAILABLE.stream()
				.filter(product -> product.getProductID().equalsIgnoreCase(productID)).findFirst();
		final Product requiredProduct = optionalRequiredProduct
				.orElseThrow(() -> new ProductNotFoundException("Product with requested ID not found",
						ErrorCodeType.PRODUCT_NOT_FOUND));
		if (request.getUpdateQuantityType() == UpdateQuantityType.DEC
				&& requiredProduct.getQuantity() - request.getQuantity() > 0) {
			requiredProduct.setQuantity(requiredProduct.getQuantity() - request.getQuantity());
		} else if (request.getUpdateQuantityType() == UpdateQuantityType.DEC
				&& requiredProduct.getQuantity() - request.getQuantity() < 0) {
			throw new ProductUpdateFailedException("Quantity is less then you want to decrease",
					ErrorCodeType.PRODUCT_QUANTITY_UPDATE_FAILED);
		} else {
			requiredProduct.setQuantity(requiredProduct.getQuantity() + request.getQuantity());
		}

		return requiredProduct;

	}

	@Override
	public List<Product> addProducts(final List<Product> products) throws ApplicationException {
		List<String> productIDs = products.stream().map(Product::getProductID).collect(Collectors.toList());
		Optional<Product> optionalProduct = ProductStore.PRODUCTS_AVAILABLE.stream()
				.filter(product -> productIDs.contains(product.getProductID())).findAny();
		if (optionalProduct.isPresent()) {
			LOGGER.info("Product id already exist");
			throw new ProductUpdateFailedException("Product id alreay exist",
					ErrorCodeType.PRODUCT_QUANTITY_UPDATE_FAILED);
		} else {
			LOGGER.info("All product ids unique");
		}

		ProductStore.PRODUCTS_AVAILABLE.addAll(products);
		return ProductStore.PRODUCTS_AVAILABLE;
	}

	@Override
	public List<Product> deleteProducts(final List<String> products) {
		final List<Product> productToBeRemoved = ProductStore.PRODUCTS_AVAILABLE.stream()
				.filter(product -> products.contains(product.getProductID())).collect(Collectors.toList());
		ProductStore.PRODUCTS_AVAILABLE.removeAll(productToBeRemoved);
		return ProductStore.PRODUCTS_AVAILABLE;
	}

}
