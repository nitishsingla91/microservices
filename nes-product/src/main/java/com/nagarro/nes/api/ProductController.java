package com.nagarro.nes.api;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.nes.dto.UpdateProductQuantityRequest;
import com.nagarro.nes.exception.ApplicationException;
import com.nagarro.nes.models.BaseResponse;
import com.nagarro.nes.models.Product;
import com.nagarro.nes.services.IProductService;
import com.nagarro.nes.validators.ProductInputValidator;

import io.swagger.annotations.Api;

/**
 * The Class ProductController.
 *
 * @author chetanmahajan
 */
@RestController
@RequestMapping(value = "/products")
@Api
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private IProductService productService;

	@Autowired
	private ProductInputValidator inputValidator;

	@GetMapping
	public ResponseEntity<BaseResponse<List<Product>>> getAllProductsBySearchText(
			@RequestParam(name = "searchProductText", required = false) final String searchProductText) {
		LOGGER.info("Requested search text:{}", searchProductText);
		return ResponseEntity.ok(new BaseResponse<>(this.productService.getAllProductsBySearchText(searchProductText)));
	}

	@GetMapping("/{productID}")
	public ResponseEntity<BaseResponse<Product>> getProductById(@PathVariable("productID") final String productID)
			throws ApplicationException {
		LOGGER.info("Request for product with id:{}", productID);
		return ResponseEntity.ok(new BaseResponse<>(this.productService.getProductById(productID)));
	}

	@PostMapping
	public ResponseEntity<BaseResponse<List<Product>>> addProducts(@RequestBody final List<Product> products)
			throws ApplicationException {
		LOGGER.info("Received Add products to store request");
		return ResponseEntity.ok(new BaseResponse<>(this.productService.addProducts(products)));
	}

	@DeleteMapping
	public ResponseEntity<BaseResponse<List<Product>>> deleteProducts(
			@RequestParam("productId") final List<String> products) {
		LOGGER.info("Recieved Delete prouducts request");
		return ResponseEntity.ok(new BaseResponse<>(this.productService.deleteProducts(products)));
	}

	@PutMapping("/{productID}/quantity")
	public ResponseEntity<BaseResponse<Product>> updateQuantity(@PathVariable("productID") final String productID,
			@Valid @RequestBody final UpdateProductQuantityRequest request, final BindingResult requestBindingResults)
			throws ApplicationException {
		LOGGER.info("Request for updating quantity received:{}", request);
		this.inputValidator.checkBindingResults(requestBindingResults);
		return ResponseEntity.ok(new BaseResponse<>(this.productService.updateQuantity(productID, request)));
	}

}
