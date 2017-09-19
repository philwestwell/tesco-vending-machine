package com.tesco.bdd.steps;

import cucumber.api.java8.En;

public class VendingMachineStepDefs implements En {
	public VendingMachineStepDefs() {
		Given("^The machine is powered off$", () -> {
			// todo - setup a powered off vending machine
		});
		When("^I power it on$", () -> {
			// todo - send power on message;
		});
		Then("^The machine sets itself to the initial state$", () -> {
			// todo - query to see whether machine is in initial state
		});
		And("^The machine notifies the user that it is ready to start vending$", () -> {
			// todo - query to see whether machine is displaying the "ready" message
		});
	}
}