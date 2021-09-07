package com.nagarro.nes.communication.product.dtos;

/**
 * The Class ProductDO.
 *
 * @author chetanmahajan
 */
public class Product {

	/** The product ID. */
	private String productID;

	/** The product name. */
	private String productName;

	/** The description. */
	private String description;

	/** The price. */
	private long price;

	/** The category. */
	private String category;

	/** The quantities. */
	private long quantity;

	/**
	 * Instantiates a new product DO.
	 */
	public Product() {
		super();
	}

	/**
	 * Instantiates a new product DO.
	 *
	 * @param productID   the product ID
	 * @param productName the product name
	 * @param description the description
	 * @param price       the price
	 * @param category    the category
	 * @param quantities  the quantities
	 */
	public Product(String productID, String productName, String description, long price, String category,
			long quantities) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.category = category;
		this.quantity = quantities;
	}

	/**
	 * Gets the product ID.
	 *
	 * @return the product ID
	 */
	public String getProductID() {
		return productID;
	}

	/**
	 * Sets the product ID.
	 *
	 * @param productID the new product ID
	 */
	public void setProductID(String productID) {
		this.productID = productID;
	}

	/**
	 * Gets the product name.
	 *
	 * @return the product name
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Sets the product name.
	 *
	 * @param productName the new product name
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public long getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(long price) {
		this.price = price;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", description=" + description
				+ ", price=" + price + ", category=" + category + ", quantity=" + quantity + "]";
	}

}
