package com.tesco.app.machine;
import java.util.Arrays;

public class CoinChangeCalculator {
	public static int[] calculateAvailableCoinsNeeded(int amountRequired, int numberOf10pCoins, int numberOf20pCoins, int numberOf50pCoins, int numberOf1PoundCoins) {
		int[] matchingCoinCombination = new int[4];

		//use the sentinel value of -1 to signify that we couldn't service the change request using the coins available
		Arrays.fill(matchingCoinCombination, -1);
		boolean isMatchingCombinationFound = false;
		// Start with the highest value coin.
		// Allowing for rounding errors, pick the highest number of these coins that are just greater than or equal to the required amount 
		for (int poundCounter = calculateStartingIndexForPounds(amountRequired, numberOf1PoundCoins); poundCounter >= 0; poundCounter--) {
			if (poundCounter * 100 > amountRequired) {
				continue;
			}
			if (poundCounter * 100 == amountRequired) {
				matchingCoinCombination = new int[] {0,0,0,poundCounter};
				isMatchingCombinationFound = true;
				break;
			}
			for (int fiftyPenceCounter = calculateStartingIndexFor50p(amountRequired, numberOf50pCoins, poundCounter); fiftyPenceCounter >= 0; fiftyPenceCounter--) {
				if (fiftyPenceCounter * 50 + poundCounter * 100 > amountRequired) {
					continue;
				}
				if (fiftyPenceCounter * 50 + poundCounter * 100 == amountRequired) {
					matchingCoinCombination = new int[] {0,0,fiftyPenceCounter,poundCounter};
					isMatchingCombinationFound = true;
					break;
				}
				for (int twentyPenceCounter = calculateStartingIndexFor20p(amountRequired, numberOf20pCoins, fiftyPenceCounter, poundCounter); twentyPenceCounter >= 0; twentyPenceCounter--) {
					if (twentyPenceCounter * 20 + fiftyPenceCounter * 50 + poundCounter * 100 > amountRequired) {
						continue;
					}
					if (twentyPenceCounter * 20 + fiftyPenceCounter * 50 + poundCounter * 100 == amountRequired) {
						matchingCoinCombination = new int[] {0,twentyPenceCounter,fiftyPenceCounter,poundCounter};
						isMatchingCombinationFound = true;
						break;
					}
					for (int tenPenceCounter = calculateStartingIndexFor10p(amountRequired, numberOf10pCoins, twentyPenceCounter, fiftyPenceCounter, poundCounter); tenPenceCounter >= 0; tenPenceCounter--) {
						if (tenPenceCounter * 10 + twentyPenceCounter * 20 + fiftyPenceCounter * 50 + poundCounter * 100 > amountRequired) {
							continue;
						}
						if (tenPenceCounter * 10 + twentyPenceCounter * 20 + fiftyPenceCounter * 50 + poundCounter * 100 == amountRequired) {
							matchingCoinCombination = new int[] {tenPenceCounter,twentyPenceCounter,fiftyPenceCounter,poundCounter};
							isMatchingCombinationFound = true;
							break;
						}
					}
					if (isMatchingCombinationFound) {
						break;
					}
				}
				if (isMatchingCombinationFound) {
					break;
				}
			}
			if (isMatchingCombinationFound) {
				break;
			}

		}
		return matchingCoinCombination;
	}
	private static int calculateStartingIndexFor10p(int amountRequired, int numberOf10pCoins, int twentyPenceCounter, int fiftyPenceCounter, int poundCounter) {
		return Math.min(numberOf10pCoins,(int) Math.floor((amountRequired - twentyPenceCounter*20 - fiftyPenceCounter*50 - poundCounter*100)/10));
	}

	private static int calculateStartingIndexFor20p(int amountRequired, int numberOf20pCoins, int fiftyPenceCounter, int poundCounter) {
		return Math.min(numberOf20pCoins,(int) Math.floor((amountRequired - fiftyPenceCounter*50 - poundCounter*100)/20));
	}

	private static int calculateStartingIndexFor50p(int amountRequired, int numberOf50pCoins, int poundCounter) {
		return Math.min(numberOf50pCoins,(int) Math.floor((amountRequired - poundCounter*100)/50));
	}

	private static int calculateStartingIndexForPounds(int amountRequired, int numberOf1PoundCoins) {
		return Math.min(numberOf1PoundCoins,(int) Math.floor(amountRequired/100));
	}
}
