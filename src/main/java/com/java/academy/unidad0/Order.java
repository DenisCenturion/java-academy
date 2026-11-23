package com.java.academy.unidad0;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private final String orderId;
	private final List<OrderItem> items;

	public Order(String orderId) {
		if (orderId == null || orderId.isBlank()) {
			throw new IllegalArgumentException("OrderId no puede estar vacio o null");
		}
		this.orderId = orderId;
		this.items = new ArrayList<OrderItem>();
	}

	public String getOrderId() {
		return orderId;
	}

	public List<OrderItem> getItems() {
		// return Collections.unmodifiableList(items);
		return List.copyOf(items);
	}

	public void addItem(OrderItem orderItem) {
		if (orderItem == null) {
			throw new IllegalArgumentException("oderItem no puede ser null");
		}

		for (OrderItem item : items) {
			if (item.getProduct().equals(orderItem.getProduct())) {
				throw new IllegalArgumentException("El producto ya existe");
			}
		}
		items.add(orderItem);
	}
}
