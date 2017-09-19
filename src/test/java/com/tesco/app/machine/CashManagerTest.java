package com.tesco.app.machine;

import static org.junit.Assert.*;
import org.junit.*;
import org.mockito.*;
import static org.mockito.Mockito.*;

import com.tesco.app.machine.exception.InsufficientFundsException;
import com.tesco.app.machine.money.Coin;
import com.tesco.app.machine.money.FiftyPence;
import com.tesco.app.machine.money.OnePound;
import com.tesco.app.machine.money.TenPence;
import com.tesco.app.machine.money.TwentyPence;
import com.tesco.app.machine.product.ProductA;
import com.tesco.app.machine.product.ProductB;
import com.tesco.app.machine.product.ProductC;

public class CashManagerTest {
	CashTransaction cashTransaction;
	private CashManager cashManagerUnderTest = null;

	@Before
	public void initialize() {
		cashTransaction = new CashTransaction();
	}

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void cashManagerCanBeConstructed() {
		cashManagerUnderTest = new CashManager(cashTransaction);
		assertNotNull(cashManagerUnderTest);
	}

	@Test
	public void addingOneCoinUpdatesTheBalance() {
		cashManagerUnderTest = new CashManager(cashTransaction);
		cashManagerUnderTest.addCoin(new TenPence());
		assertEquals(10, cashTransaction.getBalance());
	}
	@Test
	public void addingMultipleCoinsUpdatesTheBalance() {
		cashManagerUnderTest = new CashManager(cashTransaction);
		cashManagerUnderTest.addCoin(new TenPence());
		cashManagerUnderTest.addCoin(new TwentyPence());
		cashManagerUnderTest.addCoin(new FiftyPence());
		cashManagerUnderTest.addCoin(new OnePound());
		cashManagerUnderTest.addCoin(new TenPence());
		cashManagerUnderTest.addCoin(new TwentyPence());
		cashManagerUnderTest.addCoin(new OnePound());
		cashManagerUnderTest.addCoin(new TenPence());
		assertEquals(320, cashTransaction.getBalance());
	}
	@Test
	public void addingCoinsUpdatesTheCoinCountInTheCashManager() {
		cashManagerUnderTest = new CashManager(cashTransaction);
		//before count
		assertEquals(100, cashManagerUnderTest.get10pCoinCount());
		cashManagerUnderTest.addCoin(new TenPence());
		//after count
		assertEquals(101, cashManagerUnderTest.get10pCoinCount());
		assertEquals(100, cashManagerUnderTest.get20pCoinCount());
		cashManagerUnderTest.addCoin(new TwentyPence());
		//after count
		assertEquals(101, cashManagerUnderTest.get20pCoinCount());
		assertEquals(100, cashManagerUnderTest.get50pCoinCount());
		cashManagerUnderTest.addCoin(new FiftyPence());
		//after count
		assertEquals(101, cashManagerUnderTest.get50pCoinCount());
		assertEquals(100, cashManagerUnderTest.get1PoundCoinCount());
		cashManagerUnderTest.addCoin(new OnePound());
		//after count
		assertEquals(101, cashManagerUnderTest.get1PoundCoinCount());
	}
	@Test
	public void returnBalance_SingleCoin() {
		cashManagerUnderTest = new CashManager(cashTransaction);
		cashManagerUnderTest.addCoin(new TenPence());
		assertEquals(10, cashTransaction.getBalance());
		assertEquals(101, cashManagerUnderTest.get10pCoinCount());
		cashManagerUnderTest.returnBalance();
		assertEquals(0, cashTransaction.getBalance());
		assertEquals(100, cashManagerUnderTest.get10pCoinCount());
	}
	@Test
	public void returnBalance_MultipleCoins_SameCoinsReturned() {
		cashManagerUnderTest = new CashManager(cashTransaction);
		cashManagerUnderTest.addCoin(new TenPence());
		cashManagerUnderTest.addCoin(new TwentyPence());
		cashManagerUnderTest.addCoin(new FiftyPence());
		cashManagerUnderTest.addCoin(new OnePound());
		assertEquals(180, cashTransaction.getBalance());
		assertEquals(101, cashManagerUnderTest.get10pCoinCount());
		assertEquals(101, cashManagerUnderTest.get20pCoinCount());
		assertEquals(101, cashManagerUnderTest.get50pCoinCount());
		assertEquals(101, cashManagerUnderTest.get1PoundCoinCount());
		cashManagerUnderTest.returnBalance();
		assertEquals(100, cashManagerUnderTest.get10pCoinCount());
		assertEquals(100, cashManagerUnderTest.get20pCoinCount());
		assertEquals(100, cashManagerUnderTest.get50pCoinCount());
		assertEquals(100, cashManagerUnderTest.get1PoundCoinCount());
	}
	@Test
	public void returnBalance_MultipleCoins_DifferentCoinsReturned() {
		cashManagerUnderTest = new CashManager(cashTransaction);
		cashManagerUnderTest.addCoin(new TenPence());
		cashManagerUnderTest.addCoin(new TenPence());
		cashManagerUnderTest.addCoin(new TenPence());
		cashManagerUnderTest.addCoin(new TenPence());
		cashManagerUnderTest.addCoin(new TenPence());
		cashManagerUnderTest.addCoin(new FiftyPence());
		assertEquals(100, cashTransaction.getBalance());
		assertEquals(105, cashManagerUnderTest.get10pCoinCount());
		assertEquals(101, cashManagerUnderTest.get50pCoinCount());
		cashManagerUnderTest.returnBalance();
		assertEquals(0, cashTransaction.getBalance());
		assertEquals(105, cashManagerUnderTest.get10pCoinCount());
		assertEquals(99, cashManagerUnderTest.get1PoundCoinCount());
	}
	@Test
	public void isCanAfford_SingleProduct() {
		cashManagerUnderTest = new CashManager(cashTransaction);
		cashManagerUnderTest.addCoin(new FiftyPence());
		assertFalse(cashManagerUnderTest.isCanAfford(new ProductA()));
		assertFalse(cashManagerUnderTest.isCanAfford(new ProductB()));
		assertFalse(cashManagerUnderTest.isCanAfford(new ProductC()));
		cashManagerUnderTest.addCoin(new TenPence());
		assertTrue(cashManagerUnderTest.isCanAfford(new ProductA()));
		assertFalse(cashManagerUnderTest.isCanAfford(new ProductB()));
		assertFalse(cashManagerUnderTest.isCanAfford(new ProductC()));
		cashManagerUnderTest.addCoin(new TwentyPence());
		cashManagerUnderTest.addCoin(new TenPence());
		assertTrue(cashManagerUnderTest.isCanAfford(new ProductA()));
		assertFalse(cashManagerUnderTest.isCanAfford(new ProductB()));
		assertFalse(cashManagerUnderTest.isCanAfford(new ProductC()));
		cashManagerUnderTest.addCoin(new TenPence());
		assertTrue(cashManagerUnderTest.isCanAfford(new ProductA()));
		assertTrue(cashManagerUnderTest.isCanAfford(new ProductB()));
		assertFalse(cashManagerUnderTest.isCanAfford(new ProductC()));
		cashManagerUnderTest.addCoin(new FiftyPence());
		cashManagerUnderTest.addCoin(new TenPence());
		assertTrue(cashManagerUnderTest.isCanAfford(new ProductA()));
		assertTrue(cashManagerUnderTest.isCanAfford(new ProductB()));
		assertFalse(cashManagerUnderTest.isCanAfford(new ProductC()));
		cashManagerUnderTest.addCoin(new TenPence());
		assertTrue(cashManagerUnderTest.isCanAfford(new ProductA()));
		assertTrue(cashManagerUnderTest.isCanAfford(new ProductB()));
		assertTrue(cashManagerUnderTest.isCanAfford(new ProductC()));
	}
	@Test(expected=InsufficientFundsException.class) 
	public void buyProductWithInsufficientFunds() throws InsufficientFundsException {
		cashManagerUnderTest = new CashManager(cashTransaction);
		cashManagerUnderTest.addCoin(new FiftyPence());
		assertFalse(cashManagerUnderTest.isCanAfford(new ProductA()));
		cashManagerUnderTest.buyProduct(new ProductA());
	}
	
}