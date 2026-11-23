package com.java.academy.unidad0;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class ProductTest {

	@Test
	public void createProductTest() {

		String object = "SKU-1";
		Product producto = new Product(object, "Remera");

		assertEquals(Boolean.FALSE, producto.equals(null));

		Product producto00 = new Product(object, "Remera");

		assertEquals(Boolean.FALSE, producto00.equals(object));

		assertThrows(NullPointerException.class, () -> asignacionInvalida());
		assertThrows(IllegalArgumentException.class, () -> asignacionInvalida2());
		assertThrows(IllegalArgumentException.class, () -> asignacionInvalida3());

		Product producto2 = new Product(object, "Remera");
		Product producto3 = new Product("SKU-2", "Pantalon");
		Product producto4 = new Product(object, "Remera lisa");

		assertEquals(producto2.getSku(), producto4.getSku());

		assertEquals(producto2, producto4);
		assertNotEquals(producto2, producto3);
		assertNotEquals(producto3, producto4);

		Set<Product> hashSetPrueba = new HashSet<Product>();

		hashSetPrueba.add(producto2);
		hashSetPrueba.add(producto3);
		hashSetPrueba.add(producto4);

		assertEquals(2, hashSetPrueba.size());

		assertTrue(hashSetPrueba.contains(producto2));

		producto2.setName("Pantalon corto");

		assertTrue(hashSetPrueba.contains(producto2));

		assertEquals(producto2.hashCode(), producto4.hashCode());

		System.out.println(
				"HashCode PRODUCTO2: " + producto2.hashCode() + "\n" + "HashCode PRODUCTO4: " + producto4.hashCode());

		for (Product prd : hashSetPrueba) {
			System.out.println(
					"SKU: " + prd.getSku() + "\t" + "Nombre: " + prd.getName() + "\t" + "HashCode: " + prd.hashCode());

		}

	}

	@Test
	public void testProductCollection() {
		List<Product> productos = new ArrayList<>();

		productos.add(new Product("PROD-003", "Remera"));
		productos.add(new Product("PROD-001", "Pantalón"));
		productos.add(new Product("PROD-002", "Campera"));
		
		assertEquals("Remera", productos.get(0).getName());
		
		productos.sort(new ProductNameComparator());
		
		assertEquals("Campera", productos.get(0).getName());
		
		productos.sort(null);
		
		assertEquals("Pantalón", productos.get(0).getName());
		
	}
	
	@Test
	void testProductNaturalOrder() {
		List<Product> productos = new ArrayList<>();
		
		productos.add(new Product("PROD-003", "Remera"));
		productos.add(new Product("PROD-001", "Pantalón"));
		productos.add(new Product("PROD-002", "Campera"));
	    
		productos.sort(null);
		
		assertEquals("PROD-001", productos.get(0).getSku());
		assertEquals("PROD-002", productos.get(1).getSku());
		assertEquals("PROD-003", productos.get(2).getSku());
		
		
	}
	
	@Test
	void testProductNameOrder() {
		List<Product> productos = new ArrayList<>();

		productos.add(new Product("PROD-003", "Remera"));
		productos.add(new Product("PROD-001", "Pantalón"));
		productos.add(new Product("PROD-002", "Campera"));
		
		productos.sort(new ProductNameComparator());
		
		assertEquals("Campera", productos.get(0).getName());
		assertEquals("Pantalón", productos.get(1).getName());
		assertEquals("Remera", productos.get(2).getName());
		
	}
	
	@Test
	void testStock() {
		Product producto = new Product("SKU-001", "Remera");
		assertEquals(0, producto.getStock());
		
		producto.addStock(1);
		assertEquals(1, producto.getStock());
		producto.addStock(2);
		assertEquals(3, producto.getStock());
		
		assertThrows(IllegalArgumentException.class, () -> producto.addStock(0));
		assertThrows(IllegalArgumentException.class, () -> producto.addStock(-1));
		
		producto.discountStock(1);
		assertEquals(2, producto.getStock());
		
		assertThrows(IllegalArgumentException.class, () -> producto.discountStock(4));
		assertThrows(IllegalArgumentException.class, () -> producto.discountStock(0));
		assertThrows(IllegalArgumentException.class, () -> producto.discountStock(-5));
		
	}
	
	@Test
	void hasEnoughStock() {
		Product productoNuevo = new Product("SKU-123", "Remeras");
		productoNuevo.addStock(5);
		assertTrue(productoNuevo.hasEnoughStock(1));
		assertTrue(productoNuevo.hasEnoughStock(5));
		assertFalse(productoNuevo.hasEnoughStock(7));
		assertFalse(productoNuevo.hasEnoughStock(-5));
		assertFalse(productoNuevo.hasEnoughStock(0));
		
		
	}

	private Product asignacionInvalida() {
		Product producto1 = new Product(null, null);
		return producto1;
	}

	private Product asignacionInvalida2() {
		Product producto1 = new Product("", null);
		return producto1;
	}

	private Product asignacionInvalida3() {
		Product producto1 = new Product("    ", null);
		return producto1;
	}

}
