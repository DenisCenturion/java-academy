package com.java.academy.unidad0;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

	Product producto1;
	Product producto2;

	OrderItem orderItem;
	OrderItem orderItem2;

	Order order;

	@BeforeEach
	void setUp() {

		producto1 = new Product("SKU-001", "Remera");
		producto2 = new Product("SKU-002", "Pantalon");

		producto1.addStock(5);
		producto2.addStock(3);

		orderItem = new OrderItem(producto1, 1);
		orderItem2 = new OrderItem(producto2, 2);

		String orderId = "1234";
		order = new Order(orderId);

		order.addItem(orderItem);
		order.addItem(orderItem2);
	}
	
	@Test
	void exactStock() {
		Product producto = new Product("SKU-001", "Remera");
		producto.addStock(10);
		OrderItem orderItem = new OrderItem(producto, 10);
		Order order = new Order("12345");
		
		order.addItem(orderItem);
		
		OrderService.confirmOrder(order);
		
		assertEquals(0, producto.getStock());
	}

	@Test
	void validOrder() {
		assertDoesNotThrow(() -> OrderService.confirmOrder(order));
		assertEquals(4, producto1.getStock());
		assertEquals(1, producto2.getStock());
	}

	@Test
	void whitOutStock() {
		Product producto3 = new Product("SKU-001", "Remera");
		producto3.addStock(2);
		OrderItem orderItem3 = new OrderItem(producto3, 4);
		Order order3 = new Order("3");

		order3.addItem(orderItem3);

		assertThrows(IllegalArgumentException.class, () -> OrderService.confirmOrder(order3));
		
		assertEquals(2, producto3.getStock());
	}
	
	@Test
	void emptyOrder() {
		Order order4 = new Order("1234");
		assertThrows(IllegalArgumentException.class, () -> OrderService.confirmOrder(order4));
	}
	
	@Test
	void nullOrder() {
		assertThrows(IllegalArgumentException.class, () -> OrderService.confirmOrder(null));
	}
	
	@Test
	void shouldRejectOrder() {
		
		Product producto5 = new Product("SKU-005", "Remeron");
		producto5.addStock(1);
		OrderItem orderItem5 = new OrderItem(producto5, 1);
		Order order5 = new Order("5");
		order5.addItem(orderItem5);
		
		Product producto6 = new Product("SKU-006", "Muscuolsa");
		producto6.addStock(2);
		OrderItem orderItem6 = new OrderItem(producto6, 3);

		order5.addItem(orderItem6);
		
		assertThrows(IllegalArgumentException.class, () -> OrderService.confirmOrder(order5));
		
		
		assertEquals(1, producto5.getStock());
		assertEquals(2, producto6.getStock());
		
	}

}
