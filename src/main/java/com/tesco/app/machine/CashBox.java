package com.tesco.app.machine;

import com.tesco.app.machine.money.Coin;
import com.tesco.app.machine.money.CoinType;

public class CashBox {
	int countOf10pCoins = 0;
	int countOf20pCoins = 0;
	int countOf50pCoins = 0;
	int countOf1PoundCoins = 0;
	CashTransaction cashTransaction = null;

	public CashBox(CashTransaction cashTransaction) {
		this.cashTransaction = cashTransaction;
		// todo pass in the coin counts that are probably retrieved from persistent
		// storage
		this.countOf10pCoins = 100;
		this.countOf20pCoins = 100;
		this.countOf50pCoins = 100;
		this.countOf1PoundCoins = 100;
	}

	public void addCoin(Coin aCoin) {
		this.cashTransaction.addAmount(aCoin.valueInPence());
		switch (aCoin.getCoinType()) {
		case TEN_PENCE:
			this.countOf10pCoins++;
			break;
		case TWENTY_PENCE:
			this.countOf20pCoins++;
			break;
		case FIFTY_PENCE:
			this.countOf50pCoins++;
			break;
		case ONE_POUND:
			this.countOf1PoundCoins++;
			break;
		}
	}

	public void returnBalance() {
		// TODO Auto-generated method stub
	}

	public int get10pCoinCount() {
		return countOf10pCoins;
	}
	public int get20pCoinCount() {
		return countOf20pCoins;
	}
	public int get50pCoinCount() {
		return countOf50pCoins;
	}
	public int get1PoundCoinCount() {
		return countOf1PoundCoins;
	}
}
