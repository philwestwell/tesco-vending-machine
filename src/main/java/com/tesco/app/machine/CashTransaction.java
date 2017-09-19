package com.tesco.app.machine;

public class CashTransaction {
	private int totalValueInPence = 0;

	public void addAmount(int valueInPence) {
		this.totalValueInPence += valueInPence;
	}

	public int getBalance() {
		return this.totalValueInPence;
	}

	public void startNewTransaction() {
		this.totalValueInPence = 0;
	}

	public void withdrawAmount(int costInPence) {
		this.totalValueInPence -= costInPence;
	}
}
