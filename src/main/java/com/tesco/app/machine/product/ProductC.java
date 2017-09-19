package com.tesco.app.machine.product;

public class ProductC implements Product {

	@Override
	public int costInPence() {
		return 170;
	}

	@Override
	public ProductType getProductType() {
		return ProductType.PRODUCT_C;
	}

}
