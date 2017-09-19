package com.tesco.app.machine.money;

public class FiftyPence implements Coin {
	@Override
	public int valueInPence() {
		return 50;
	}

	@Override
	public CoinType getCoinType() {
		return CoinType.FIFTY_PENCE;
	}
}
