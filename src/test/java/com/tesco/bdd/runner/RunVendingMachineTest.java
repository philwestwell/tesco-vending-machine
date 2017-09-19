package com.tesco.bdd.runner;
 
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber; 

@RunWith(Cucumber.class) 
@CucumberOptions(
		dryRun = true,
		features = {"classpath:features/vendingMachinePower.feature","classpath:features/vendingMachinePurchase.feature"}
) 
public class RunVendingMachineTest { }
