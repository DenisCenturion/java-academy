package com.java.academy.unidad0;

import java.util.Objects;

public class OrderItem {

	private final Product product;
	private final int quantity;

	public OrderItem(Product product, int quantity) {
		this.product = Objects.requireNonNull(product, "Product obligatorio");
		if (quantity <= 0) {
			throw new IllegalArgumentException("Quantity debe ser mayor a 0");
		}
		this.quantity = quantity;

	}

	public int getQuantity() {
		return quantity;
	}

	public Product getProduct() {
		return product;
	}

}
