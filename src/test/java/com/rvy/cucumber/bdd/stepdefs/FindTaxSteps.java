package com.rvy.cucumber.bdd.stepdefs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
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
public class FindTaxSteps extends AbstractSteps implements En {

	boolean notNull = false;
	@Autowired
	Taxation tax;
	List<Taxation> taxList = new ArrayList<Taxation>();
	boolean found = false;
	public FindTaxSteps() {



		Given("The following tax exists and Admin wants to find the tax by ID", (DataTable taxDt) -> {
			testContext().reset();

			List<List<String>> taxData = taxDt.asLists(String.class);

			taxList.add(populateTax(taxData.get(1)));
			taxList.add(populateTax(taxData.get(2)));

		});

		When("admin wants to find the tax with ID {string}", (String id) -> {
			//      String createUrl = "rvy/api/cm/v1/s";
			//
			//      // AbstractSteps class makes the POST call and stores response in TestContext
			//      executePost(createTaxUrl);
			Integer taxId = Integer.parseInt(id);
			for(Taxation c: taxList) {
				if(c.getTaxId().equals(taxId)) {
					found =true;
				}
			}

		});

		/**
		 * This can be moved to a Class named 'CommonSteps.java so that it can be reused.
		 */
		Then("the tax is found", () -> {   
			assertTrue(found);
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

