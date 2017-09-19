package com.tesco.app.machine;

public class CashTransaction {
	private int totalValueInPence = 0;
	public void addAmount(int valueInPence) {
		totalValueInPence += valueInPence;
	}
	public int getBalance() {
		return totalValueInPence;
	}
}
