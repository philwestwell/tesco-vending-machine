package com.tesco.app.machine;
import static org.junit.Assert.*;
import org.junit.*;
import org.mockito.*;
import static org.mockito.Mockito.*;

import com.tesco.app.machine.money.Coin;
import com.tesco.app.machine.money.FiftyPence;
import com.tesco.app.machine.money.TenPence;
import com.tesco.app.machine.product.Product;
import com.tesco.app.machine.product.ProductA;
import com.tesco.app.machine.product.ProductType;
public class VendingMachineTest {
	@Mock
	CashManager cashBoxMock;
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
    		vendingMachineUnderTest = new VendingMachine(cashBoxMock, null);
    		Coin nextCoin = new TenPence();
    		vendingMachineUnderTest.insertCoin(nextCoin);
    		verify(cashBoxMock).addCoin(nextCoin);
    }
    @Test
    public void coinsAreReturned_SingleCoin() {
    		vendingMachineUnderTest = new VendingMachine(cashBoxMock, null);
    		Coin nextCoin = new TenPence();
    		vendingMachineUnderTest.insertCoin(nextCoin);
    		vendingMachineUnderTest.returnCoins();
    		verify(cashBoxMock).returnBalance();
    }
    @Test
    public void productCanBeDispensed_ExactMoney() {
    		vendingMachineUnderTest = new VendingMachine(cashBoxMock, productCompartmentMock);    		
    		vendingMachineUnderTest.insertCoin(new TenPence());
    		vendingMachineUnderTest.insertCoin(new FiftyPence());
    		Product nextProduct = new ProductA();
    		vendingMachineUnderTest.buyProduct(nextProduct);    		
    		verify(productCompartmentMock).dispenseProduct(nextProduct);
    }
}