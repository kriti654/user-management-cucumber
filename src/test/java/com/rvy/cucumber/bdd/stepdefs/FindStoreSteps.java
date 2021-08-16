package com.rvy.cucumber.bdd.stepdefs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rvy.store.entity.Store;

import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

public class FindStoreSteps extends AbstractSteps implements En {
	boolean notNull = false;
	@Autowired
	Store store;
	List<Store> storeList = new ArrayList<Store>();

	boolean found = false;

	public FindStoreSteps() {



		Given("The following store exists and Admin wants to find the store by ID", (DataTable storeDt) -> {
			testContext().reset();

			List<List<String>> storeData = storeDt.asLists(String.class);

			storeList.add(populateStore(storeData.get(1)));
			storeList.add(populateStore(storeData.get(2)));

		});

		When("admin wants to find the store with ID {string}", (String id) -> {
			//      String createUrl = "rvy/api/cm/v1/s";
			//
			//      // AbstractSteps class makes the POST call and stores response in TestContext
			//      executePost(createStoreUrl);
			Integer storeId = Integer.parseInt(id);
			for(Store c: storeList) {
				if(c.getStoreId().equals(storeId)) {
					found =true;
				}
			}

		});

		/**
		 * This can be moved to a Class named 'CommonSteps.java so that it can be reused.
		 */
		Then("the store is found", () -> {   
			assertTrue(found);
		});

	}
	private Store populateStore(List<String> list) {

		store.setStoreId(Integer.parseInt(list.get(0)));
		store.setName(list.get(1));
		store.setAddress(list.get(2));
		store.setMobileNumber(Long.parseLong(list.get(3)));
		store.setEmail(list.get(4));
		store.setRegion(null);
		store.setUserList(null);
		return store;
	}

}
