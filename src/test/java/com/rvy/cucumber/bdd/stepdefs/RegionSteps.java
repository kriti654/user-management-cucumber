package com.rvy.cucumber.bdd.stepdefs;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rvy.store.entity.Region;

import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

public class RegionSteps extends AbstractSteps implements En{
	
	boolean notNull = false;
	@Autowired
	Region region;
	
	public RegionSteps() {


		Given("Admin wants to create a region with the following attributes", (DataTable regionDt) -> {
			testContext().reset();

			List<List<String>> regionData = regionDt.asLists(String.class);
			region = populateRegion(regionData.get(1));

		});

		When("admin saves the new region {string}", (String testContext) -> {
			
			//      // AbstractSteps class makes the POST call and stores response in TestContext
			//      executePost(createregionUrl);

			notNull = region.getName().isEmpty()? false:true;
		});

		/**
		 * This can be moved to a Class named 'CommonSteps.java so that it can be reused.
		 */
		Then("the save {string}", (String expectedResult) -> {
			assertTrue(notNull);
		});

	}
	private Region populateRegion(List<String> list) {
		// TODO Auto-generated method stub
		region.setRegionId(Integer.parseInt(list.get(0)));
		region.setName(list.get(1));
		region.setCity(list.get(2));
		region.setState(list.get(3));
		region.setCountry(list.get(4));
		region.setStoreList(null);
		region.setTax(null);
		return region;
	}
	

}
