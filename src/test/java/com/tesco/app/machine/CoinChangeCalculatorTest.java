package com.tesco.app.machine;

import static org.junit.Assert.*;
import org.junit.*;

public class CoinChangeCalculatorTest {
	@Before
	public void initialize() {
	}
	@Test
	public void changeIsAMultipleOfPounds() {
		assertArrayEquals(new int[] {0,0,0,4}, CoinChangeCalculator.calculateAvailableCoinsNeeded(400, 10, 20, 10, 10));
	}
	@Test
	public void changeIsAMultipleOfPounds_InsufficientCoins() {
		assertArrayEquals(new int[] {-1,-1,-1,-1}, CoinChangeCalculator.calculateAvailableCoinsNeeded(1100, 0, 0, 0, 10));
	}
	@Test
	public void changeIsAnOddMultipleOf50p_InsufficientCoins() {
		assertArrayEquals(new int[] {-1,-1,-1,-1}, CoinChangeCalculator.calculateAvailableCoinsNeeded(350, 0, 0, 0, 10));
	}
	@Test
	public void changeRequires50p_SufficientCoins() {
		assertArrayEquals(new int[] {0,0,1,3}, CoinChangeCalculator.calculateAvailableCoinsNeeded(350, 10, 10, 10, 10));
	}
	@Test
	public void changeRequires50p_SufficientCoinsNoHigherDenominations() {
		assertArrayEquals(new int[] {0,0,7,0}, CoinChangeCalculator.calculateAvailableCoinsNeeded(350, 0, 0, 10, 0));
	}
	@Test
	public void changeRequires20p_SufficientCoins() {
		assertArrayEquals(new int[] {0,1,0,3}, CoinChangeCalculator.calculateAvailableCoinsNeeded(320, 10, 10, 10, 10));
	}
	@Test
	public void changeRequires20p_SufficientCoinsNoHigherDenominations() {
		assertArrayEquals(new int[] {0,18,0,0}, CoinChangeCalculator.calculateAvailableCoinsNeeded(360, 0, 50, 0, 0));
	}
	@Test
	public void changeRequires20pAnd50p_SufficientCoinsNoHigherDenominations() {
		assertArrayEquals(new int[] {0,18,2,0}, CoinChangeCalculator.calculateAvailableCoinsNeeded(460, 0, 50, 2, 0));
	}
	@Test
	public void changeRequires10p_SufficientCoins() {
		assertArrayEquals(new int[] {1,0,0,3}, CoinChangeCalculator.calculateAvailableCoinsNeeded(310, 10, 10, 10, 10));
	}
	@Test
	public void changeRequires10p_SufficientCoinsNoHigherDenominations() {
		assertArrayEquals(new int[] {33,0,1,0}, CoinChangeCalculator.calculateAvailableCoinsNeeded(380, 40, 0, 1, 0));
	}
	@Test
	@Ignore
	public void changeRequires10p20p50pAnd1pound_SufficientCoins() {
		assertArrayEquals(new int[] {1,1,1,3}, CoinChangeCalculator.calculateAvailableCoinsNeeded(380, 10, 10, 10, 10));
	}
	}