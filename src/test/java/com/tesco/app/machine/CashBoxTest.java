package com.tesco.app.machine;

import static org.junit.Assert.*;
import org.junit.*;
import org.mockito.*;
import static org.mockito.Mockito.*;

import com.tesco.app.machine.money.Coin;
import com.tesco.app.machine.money.FiftyPence;
import com.tesco.app.machine.money.OnePound;
import com.tesco.app.machine.money.TenPence;
import com.tesco.app.machine.money.TwentyPence;

public class CashBoxTest {
	CashTransaction cashTransaction;
	private CashBox cashBoxUnderTest = null;

	@Before
	public void initialize() {
		cashTransaction = new CashTransaction();
	}

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void cashBoxCanBeConstructed() {
		cashBoxUnderTest = new CashBox(cashTransaction);
		assertNotNull(cashBoxUnderTest);
	}

	@Test
	public void addingOneCoinUpdatesTheBalance() {
		cashBoxUnderTest = new CashBox(cashTransaction);
		cashBoxUnderTest.addCoin(new TenPence());
		assertEquals(10, cashTransaction.getBalance());
	}
	@Test
	public void addingMultipleCoinsUpdatesTheBalance() {
		cashBoxUnderTest = new CashBox(cashTransaction);
		cashBoxUnderTest.addCoin(new TenPence());
		cashBoxUnderTest.addCoin(new TwentyPence());
		cashBoxUnderTest.addCoin(new FiftyPence());
		cashBoxUnderTest.addCoin(new OnePound());
		cashBoxUnderTest.addCoin(new TenPence());
		cashBoxUnderTest.addCoin(new TwentyPence());
		cashBoxUnderTest.addCoin(new OnePound());
		cashBoxUnderTest.addCoin(new TenPence());
		assertEquals(320, cashTransaction.getBalance());
	}
	@Test
	public void addingCoinsUpdatesTheCoinCountInTheCashBox() {
		cashBoxUnderTest = new CashBox(cashTransaction);
		//before count
		assertEquals(100, cashBoxUnderTest.get10pCoinCount());
		cashBoxUnderTest.addCoin(new TenPence());
		//after count
		assertEquals(101, cashBoxUnderTest.get10pCoinCount());
		assertEquals(100, cashBoxUnderTest.get20pCoinCount());
		cashBoxUnderTest.addCoin(new TwentyPence());
		//after count
		assertEquals(101, cashBoxUnderTest.get20pCoinCount());
		assertEquals(100, cashBoxUnderTest.get50pCoinCount());
		cashBoxUnderTest.addCoin(new FiftyPence());
		//after count
		assertEquals(101, cashBoxUnderTest.get50pCoinCount());
		assertEquals(100, cashBoxUnderTest.get1PoundCoinCount());
		cashBoxUnderTest.addCoin(new OnePound());
		//after count
		assertEquals(101, cashBoxUnderTest.get1PoundCoinCount());
	}
	@Test
	@Ignore
	public void returnBalance_SimpleCase() {
		cashBoxUnderTest = new CashBox(cashTransaction);
		cashBoxUnderTest.addCoin(new TenPence());
		assertEquals(320, cashTransaction.getBalance());
	}
}