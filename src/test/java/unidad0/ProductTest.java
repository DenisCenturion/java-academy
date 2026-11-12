package unidad0;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
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
		
		
		
		assertThrows(NullPointerException.class,  () -> asignacionInvalida());
		assertThrows(IllegalArgumentException.class,  () -> asignacionInvalida2());
		assertThrows(IllegalArgumentException.class,  () -> asignacionInvalida3());
		
		
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
		
		assertTrue( hashSetPrueba.contains(producto2));
		
		producto2.setName("Pantalon corto");
		
		assertTrue( hashSetPrueba.contains(producto2));
		
		assertEquals(producto2.hashCode(), producto4.hashCode());
		
		System.out.println("HashCode PRODUCTO2: " + producto2.hashCode() + "\n" + "HashCode PRODUCTO4: " + producto4.hashCode());
		
		
		for(Product prd : hashSetPrueba) {
			System.out.println("SKU: "+ prd.getSku() + "\t" + "Nombre: " + prd.getName() + "\t" + "HashCode: " + prd.hashCode());
			
		}
		
		
		
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
