package com.tesco.app.machine.product;

public class ProductA implements Product {

	@Override
	public int costInPence() {
		return 60;
	}

	@Override
	public ProductType getProductType() {
		return ProductType.PRODUCT_A;
	}

}
