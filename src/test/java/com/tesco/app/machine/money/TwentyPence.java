package com.tesco.app.machine.money;

public class TwentyPence implements Coin {
	@Override
	public int valueInPence() {
		return 20;
	}
	@Override
	public CoinType getCoinType() {
		return CoinType.TWENTY_PENCE;
	}

}
