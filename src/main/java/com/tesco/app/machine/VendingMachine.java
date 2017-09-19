package com.tesco.app.machine;

import com.tesco.app.machine.money.Coin;

/**
 * Encapsulates the state of a vending machine and the operations that can be performed on it. 
 * Responsibilities:
 * - Keep track of the vending machine components: CashBox, ProductCompartment
 * - This is the system class, so it orchestrates the actions on the machine components 
 */
public class VendingMachine {
	private CashBox cashBox = null;
	public VendingMachine(CashBox cashBox) {
		super();
		this.cashBox = cashBox;
	}
	
	public boolean isOn() {
		return false;
	}
	
	public void setOn() {
	}
	
	public void setOff() {
	}

	public void insertCoin(Coin nextCoin) {
		//todo establish whether the coin slot hardware goes to a "auto coin return" state when off, or whether this is just a logical "off" state
		//for now, the safest course of action is to reject coins if the machine is off
		
		this.cashBox.addCoin(nextCoin);
	}
}
