package com.tesco.app.machine.product;

public class ProductB implements Product {

	@Override
	public int getCostInPence() {
		return 100;
	}

	@Override
	public ProductType getProductType() {
		return ProductType.PRODUCT_B;
	}
}
