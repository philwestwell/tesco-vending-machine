package com.tesco.app.machine;

import com.tesco.app.machine.product.Product;
import com.tesco.app.machine.product.ProductType;

public class ProductCompartment {
	private int countOfProductA = 0;
	private int countOfProductB = 0;
	private int countOfProductC = 0;
	public ProductCompartment() {
		this.countOfProductA = 20;
		this.countOfProductB = 20;
		this.countOfProductC = 20;
	}
	public void dispenseProduct(Product aProduct) {
		switch(aProduct.getProductType()) {
		case PRODUCT_A:
			this.countOfProductA -= 1;
			break;
		case PRODUCT_B:
			this.countOfProductB -= 1;
			break;
		case PRODUCT_C:
			this.countOfProductC -= 1;
			break;
		}
	}
	public int getProductACount() {
		return this.countOfProductA;
	}
	public int getProductBCount() {
		return this.countOfProductB;
	}
	public int getProductCCount() {
		return this.countOfProductC;
	}

}
