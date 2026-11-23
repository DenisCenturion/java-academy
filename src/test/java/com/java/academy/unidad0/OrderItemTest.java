package com.java.academy.unidad0;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class OrderItemTest {
	
	@Test
	public void validCreation() {
		String sku = "SKU-1";
		Product producto = new Product(sku, "Remera");
		
		OrderItem orderItem = new OrderItem(producto, 1);
		assertNotNull(orderItem);
		assertEquals("Remera", orderItem.getProduct().getName());
		assertEquals("SKU-1", orderItem.getProduct().getSku());
		assertEquals(1, orderItem.getQuantity());
	}
	
	@Test
	public void shouldRejectNullProduct() {
		assertThrows(NullPointerException.class, () -> creacionInvalidaProductoOrder());
		
	}
	@Test
	public void shouldRejectZeroQuantity() {
		assertThrows(IllegalArgumentException.class, () -> creacionInvalidaCantidadOrder());
	}
	
	@Test
	public void shouldRejectNegativeQuantity() {
		assertThrows(IllegalArgumentException.class, () -> new OrderItem(new Product("SKU-004","Remera"), -4));
	}
	
	private OrderItem creacionInvalidaCantidadOrder() {
		String sku = "SKU-1";
		Product producto = new Product(sku, "Remera");
		
		return new OrderItem(producto, 0);
	}

	private OrderItem creacionInvalidaProductoOrder() {
		
		return new OrderItem(null, 1);
		
	}


}
