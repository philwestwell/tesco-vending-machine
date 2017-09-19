package com.tesco.app.machine;

import com.tesco.app.machine.money.Coin;

public class CashBox {
	CashTransaction cashTransaction = null;
	public CashBox(CashTransaction cashTransaction) {
		this.cashTransaction = cashTransaction;
	}
	public void addCoin(Coin aCoin) {
		this.cashTransaction.addAmount(aCoin.valueInPence());
	}
}

