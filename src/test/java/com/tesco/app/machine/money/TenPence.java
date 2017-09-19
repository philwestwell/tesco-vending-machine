package com.tesco.app.machine.money;

public class TenPence implements Coin {
	@Override
	public int valueInPence() {
		return 10;
	}
	@Override
	public CoinType getCoinType() {
		return CoinType.TEN_PENCE;
	}

}
