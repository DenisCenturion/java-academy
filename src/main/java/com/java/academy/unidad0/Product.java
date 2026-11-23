package com.java.academy.unidad0;

import java.util.Objects;

public class Product implements Comparable<Product>{
	private final String sku;
	private String name;
	
	private int stock = 0;

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

	@Override
	public int compareTo(Product other) {
		return this.sku.compareTo(other.sku);
	}

	public int getStock() {
		return stock;
	}
	
	public boolean hasEnoughStock(int quantity) {
		return quantity > 0 && this.stock >= quantity;
		
	}
	
	public void addStock(int stock) {
		if(stock <= 0) {
			throw new IllegalArgumentException("El stock inicial debe ser mayor a 0");
		}
		this.stock += stock;
	}

	public void discountStock(int quantity) {
		if(!hasEnoughStock(quantity) ) {
			throw new IllegalArgumentException("El stock a descontar no puede ser negativo ni mayor al stock existente");
		}
		this.stock-=quantity;
	}

}
