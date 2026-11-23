package com.java.academy.unidad0;

import java.util.List;

public class OrderService {

	private OrderService() {

	}

	public static void confirmOrder(Order order) {
		if (order == null || order.getItems().isEmpty()) {
			throw new IllegalArgumentException("La orden no puede estar vacia.");
		}

		validateStock(order);
		disccountFromStock(order);

	}

	private static void validateStock(Order order) {
		List<OrderItem> items = order.getItems();

		for (OrderItem currentItem : items) {
			Product currentProduct = currentItem.getProduct();
			if (!currentProduct.hasEnoughStock(currentItem.getQuantity())) {
				throw new IllegalArgumentException();
			}
		}

	}

	private static void disccountFromStock(Order order) {
		List<OrderItem> items = order.getItems();

		for (OrderItem currentItem : items) {
			Product currentProduct = currentItem.getProduct();
			currentProduct.discountStock(currentItem.getQuantity());
		}

	}

}
