package com.tesco.app.machine;

import com.tesco.app.machine.money.Coin;
import com.tesco.app.machine.product.Product;
import com.tesco.app.machine.product.ProductType;

/**
 * Encapsulates the state of a vending machine and the operations that can be performed on it. 
 * Responsibilities:
 * - Keep track of the vending machine components: CashManager, ProductCompartment
 * - This is the system class, so it orchestrates the actions on the machine components 
 */
public class VendingMachine {
	private CashManager cashManager = null;
	private boolean isOn = false;
	private ProductCompartment productCompartment = null;
	public VendingMachine(CashManager cashManager, ProductCompartment productCompartment) {
		super();
		this.cashManager = cashManager;
		this.productCompartment = productCompartment;
		this.isOn = true;
	}
	
	public boolean isOn() {
		return this.isOn;
	}
	
	public void setOn() {
		this.isOn = true;
	}
	
	public void setOff() {
		this.isOn = false;
	}
	
	public void insertCoin(Coin aCoin) {
		//todo establish whether the coin slot hardware goes to a "auto coin return" state when off, or whether this is just a logical "off" state
		//todo for now, the safest course of action is to reject coins if the machine is off
		if (!isOn()) {
			//todo - work out how to reject an inserted coin
		} else {
			this.cashManager.addCoin(aCoin);
		}
	}
	public void returnCoins() {
		this.cashManager.returnBalance();
	}

	public void buyProduct(Product aProduct) {
		this.productCompartment.dispenseProduct(aProduct);
	}
}
