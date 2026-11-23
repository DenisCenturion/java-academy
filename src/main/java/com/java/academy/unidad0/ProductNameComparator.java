package com.java.academy.unidad0;

import java.util.Comparator;

public class ProductNameComparator implements Comparator<Product> {

	@Override
	public int compare(Product producto1, Product producto2) {
		return producto1.getName().compareTo(producto2.getName());
	}

}
