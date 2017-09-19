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
import com.tesco.app.machine.product.Product;
import com.tesco.app.machine.product.ProductA;
import com.tesco.app.machine.product.ProductC;
import com.tesco.app.machine.product.ProductType;
public class VendingMachineTest {
	@Mock
	CashManager cashManagerMock;
	@Mock
	ProductCompartment productCompartmentMock;
	private 	VendingMachine vendingMachineUnderTest = null;
	@Before
	public void initialize() {
		
	}
	@Before public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void vendingMachineCanBeConstructed() {
    		vendingMachineUnderTest = new VendingMachine(new CashManager(new CashTransaction()), null);
        assertNotNull(vendingMachineUnderTest);
    }
    @Test
    public void tenPenceCoinCanBeInsertedWithoutFailing() {
    		vendingMachineUnderTest = new VendingMachine(new CashManager(new CashTransaction()), null);
    		Coin nextCoin = new TenPence();
    		vendingMachineUnderTest.insertCoin(nextCoin);
    }
    @Test
    public void tenPenceCoinCanBeInsertedAndIsPassedToCashManager() {
    		vendingMachineUnderTest = new VendingMachine(cashManagerMock, null);
    		Coin nextCoin = new TenPence();
    		vendingMachineUnderTest.insertCoin(nextCoin);
    		verify(cashManagerMock).addCoin(nextCoin);
    }
    @Test
    public void coinsAreReturned_SingleCoin() {
    		vendingMachineUnderTest = new VendingMachine(cashManagerMock, null);
    		Coin nextCoin = new TenPence();
    		vendingMachineUnderTest.insertCoin(nextCoin);
    		vendingMachineUnderTest.returnCoins();
    		verify(cashManagerMock).returnBalance();
    }
    @Test
    public void productCanBeDispensed_ExactMoney() throws Exception {
    		vendingMachineUnderTest = new VendingMachine(new CashManager(new CashTransaction()), productCompartmentMock);    		
    		vendingMachineUnderTest.insertCoin(new TenPence());
    		vendingMachineUnderTest.insertCoin(new FiftyPence());
    		Product nextProduct = new ProductA();
    		vendingMachineUnderTest.buyProduct(nextProduct);    		
    		verify(productCompartmentMock).dispenseProduct(nextProduct);
    }
    //todo - requires greater combination of tests
    @Test
    public void productCanBeDispensed_WithChange() throws Exception {
    		CashTransaction aCashTransaction = new CashTransaction();
    		CashManager aCashManager = new CashManager(aCashTransaction);
    		ProductCompartment aProductCompartment = new ProductCompartment();
    		vendingMachineUnderTest = new VendingMachine(aCashManager, aProductCompartment);    		
    		vendingMachineUnderTest.insertCoin(new OnePound());
    		vendingMachineUnderTest.insertCoin(new OnePound());
    		vendingMachineUnderTest.insertCoin(new TwentyPence());
    		assertEquals(220, aCashTransaction.getBalance());
    		assertEquals(100, aCashManager.get10pCoinCount());
    		assertEquals(101, aCashManager.get20pCoinCount());
    		assertEquals(100, aCashManager.get50pCoinCount());
    		assertEquals(102, aCashManager.get1PoundCoinCount());
    		assertEquals(20, aProductCompartment.getProductCCount());
    		Product nextProduct = new ProductC();
    		vendingMachineUnderTest.buyProduct(nextProduct);    		
    		assertEquals(0, aCashTransaction.getBalance());
    		assertEquals(100, aCashManager.get10pCoinCount());
    		assertEquals(101, aCashManager.get20pCoinCount());
    		assertEquals(99, aCashManager.get50pCoinCount());
    		assertEquals(102, aCashManager.get1PoundCoinCount());
    		assertEquals(19, aProductCompartment.getProductCCount());
    }
}