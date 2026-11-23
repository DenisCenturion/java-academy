package com.java.academy.unidad0;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderTest {

	Product producto1;
	Product producto2;

	OrderItem orderItem;
	OrderItem orderItem2;

	Order order;

	@BeforeEach
	void setUp() {

		producto1 = new Product("SKU-001", "Remera");
		producto2 = new Product("SKU-002", "Pantalon");

		orderItem = new OrderItem(producto1, 1);
		orderItem2 = new OrderItem(producto2, 3);

		String orderId = "1234";
		order = new Order(orderId);

		order.addItem(orderItem);
		order.addItem(orderItem2);

	}

	// Crear un pedido válido y verificar que inicialmente no contiene ítems.
	@Test
	public void initializeOrder() {
		String orderId = "1234";
		Order order = new Order(orderId);

		assertEquals("1234", order.getOrderId());
		assertNotNull(order.getItems());
		assertTrue(order.getItems().isEmpty());

	}

	// Agregar dos productos diferentes y comprobar que conserva el orden.
	@Test
	public void testOrderinOrder() {
		assertEquals("SKU-001", order.getItems().get(0).getProduct().getSku());
		assertEquals("SKU-002", order.getItems().get(1).getProduct().getSku());
	}

	// Intentar agregar dos productos con el mismo SKU y verificar que se rechaza el
	// segundo.
	@Test
	public void diferentProductsIntOrder() {

		Product producto3 = new Product("SKU-002", "Pantalon");
		OrderItem orderItem3 = new OrderItem(producto3, 2);

		assertThrows(IllegalArgumentException.class, () -> order.addItem(orderItem3));
	}

	// Intentar agregar un OrderItem nulo.
	@Test
	public void shouldRejectNullOrderItem() {
		
		OrderItem orderItem4 = null;
		Order order2 = new Order("12345");

		assertThrows(IllegalArgumentException.class, () -> order2.addItem(orderItem4));

	}
	
	//Intentar modificar la colección obtenida mediante getItems() y verificar que no se modifica la colección interna.
	@Test
	public void canNotChangeList() {
		
		Product producto4 = new Product("SKU-004", "Pantalon");

		OrderItem orderItem4 = new OrderItem(producto4, 1);
		
		List<OrderItem> itemsView = order.getItems();
		
		assertThrows(UnsupportedOperationException.class, () -> itemsView.add(orderItem4));
		
		assertEquals("SKU-001", order.getItems().get(0).getProduct().getSku());
		assertEquals("SKU-002", order.getItems().get(1).getProduct().getSku());

	}

	@Test
	public void invalidId() {
		assertThrows(IllegalArgumentException.class, () -> new Order(null));
		assertThrows(IllegalArgumentException.class, () -> new Order(""));
		assertThrows(IllegalArgumentException.class, () -> new Order("  "));
	}

}
