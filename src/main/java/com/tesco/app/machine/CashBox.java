package com.tesco.app.machine;

import static org.junit.Assert.assertEquals;

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
		int[] coinsToReturn = CoinChangeCalculator.calculateAvailableCoinsNeeded(this.cashTransaction.getBalance(), this.countOf10pCoins, this.countOf20pCoins, this.countOf50pCoins, this.countOf1PoundCoins);
		//todo - go back to stakeholders to decide how to deal with insufficient coins
		if (coinsToReturn[0] > -1) {
			this.countOf10pCoins -= coinsToReturn[0];
			this.countOf20pCoins -= coinsToReturn[1];
			this.countOf50pCoins -= coinsToReturn[2];
			this.countOf1PoundCoins -= coinsToReturn[3];
		}
		this.cashTransaction.startNewTransaction();
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
