package com.tesco.app.machine.product;

import com.tesco.app.machine.ItemWithCashValue;

public interface Product extends ItemWithCashValue {
	ProductType getProductType();
}
