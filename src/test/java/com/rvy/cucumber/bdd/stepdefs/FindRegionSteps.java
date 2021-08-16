package com.rvy.cucumber.bdd.stepdefs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rvy.store.entity.Region;

import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

public class FindRegionSteps extends AbstractSteps implements En {
	boolean notNull = false;
	@Autowired
	Region region;
	List<Region> regionList = new ArrayList<Region>();
	boolean found = false;
	public FindRegionSteps() {



		Given("The following region exists and Admin wants to find the region by ID", (DataTable regionDt) -> {
			testContext().reset();

			List<List<String>> regionData = regionDt.asLists(String.class);

			regionList.add(populateRegion(regionData.get(1)));
			regionList.add(populateRegion(regionData.get(2)));

		});

		When("admin wants to find the region with ID {string}", (String id) -> {
			//      String createUrl = "rvy/api/cm/v1/s";
			//
			//      // AbstractSteps class makes the POST call and stores response in TestContext
			//      executePost(createRegionUrl);
			Integer regionId = Integer.parseInt(id);
			for(Region c: regionList) {
				if(c.getRegionId().equals(regionId)) {
					found =true;
				}
			}

		});

		/**
		 * This can be moved to a Class named 'CommonSteps.java so that it can be reused.
		 */
		Then("the region is found", () -> {   
			assertTrue(found);
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
