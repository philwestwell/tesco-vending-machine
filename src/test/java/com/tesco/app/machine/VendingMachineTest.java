package com.tesco.app.machine;
import static org.junit.Assert.*;
import org.junit.*;
import org.mockito.*;
import static org.mockito.Mockito.*;

import com.tesco.app.machine.money.Coin;
import com.tesco.app.machine.money.TenPence;
public class VendingMachineTest {
	@Mock
	CashBox cashBoxMock;
	private 	VendingMachine vendingMachineUnderTest = null;
	@Before
	public void initialize() {
		
	}
	@Before public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void vendingMachineCanBeConstructed() {
    		vendingMachineUnderTest = new VendingMachine(new CashBox(new CashTransaction()));
        assertNotNull(vendingMachineUnderTest);
    }
    @Test
    public void tenPenceCoinCanBeInsertedWithoutFailing() {
    		vendingMachineUnderTest = new VendingMachine(new CashBox(new CashTransaction()));
    		Coin nextCoin = new TenPence();
    		vendingMachineUnderTest.insertCoin(nextCoin);
    }
    @Test
    public void tenPenceCoinCanBeInsertedAndIsPassedToCashBox() {
    		vendingMachineUnderTest = new VendingMachine(cashBoxMock);
    		Coin nextCoin = new TenPence();
    		vendingMachineUnderTest.insertCoin(nextCoin);
    		verify(cashBoxMock).addCoin(nextCoin);
    }
}