package com.rvy.cucumber.bdd.stepdefs;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rvy.store.entity.Taxation;

import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

/**
 * Step Definition Class for Employee.
 *
 * <p>Uses Java Lambda style step definitions so that we don't need to worry
 * about method naming for each step definition</p>
 */
public class TaxationSteps extends AbstractSteps implements En {

	boolean notNull = false;
	@Autowired
	Taxation tax;
	
	public TaxationSteps() {


		Given("Admin wants to create a tax with the following attributes", (DataTable taxDt) -> {
			testContext().reset();

			List<List<String>> taxData = taxDt.asLists(String.class);
			tax = populateTax(taxData.get(1));

		});

		When("admin saves the new tax {string}", (String testContext) -> {
			
			//      // AbstractSteps class makes the POST call and stores response in TestContext
			//      executePost(createTaxUrl);

			notNull = tax.getName().isEmpty()? false:true;
		});

		/**
		 * This can be moved to a Class named 'CommonSteps.java so that it can be reused.
		 */
		Then("the save {string}", (String expectedResult) -> {
			assertTrue(notNull);
		});

	}
	private Taxation populateTax(List<String> list) {
		Taxation tax = new Taxation();

		tax.setTaxId(Integer.parseInt(list.get(0)));
		tax.setCode(Integer.parseInt(list.get(1)));
		tax.setName(list.get(2));
		tax.setTaxAmount(Float.parseFloat(list.get(3)));
		tax.setEffectiveDate(LocalDate.parse(list.get(4)));
		tax.setRegionList(null);
		return tax;
	}

}
