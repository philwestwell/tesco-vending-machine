package com.tesco.app.machine.money;

public class OnePound implements Coin {
	@Override
	public int valueInPence() {
		return 100;
	}

	@Override
	public CoinType getCoinType() {
		return CoinType.ONE_POUND;
	}
}
