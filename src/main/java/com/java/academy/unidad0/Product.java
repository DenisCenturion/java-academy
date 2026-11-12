package com.java.academy.unidad0;

import java.util.Objects;

public class Product {
	private final String sku;
	private String name;

	public Product(String sku, String name) {
		this.sku = Objects.requireNonNull(sku, "No se permiten SKU invalidos");
		if( sku.isBlank()) {
			throw new IllegalArgumentException("SKU invalido");
		}
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSku() {
		return sku;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;

		if (object == null || getClass() != object.getClass())
			return false;

		Product product = (Product) object;

		if (product.getSku().equals(this.getSku())) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public int hashCode() {
		return Objects.hash(sku);

	}

}
