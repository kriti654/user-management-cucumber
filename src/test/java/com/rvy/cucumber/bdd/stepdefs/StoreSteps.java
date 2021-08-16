package com.rvy.cucumber.bdd.stepdefs;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rvy.store.entity.Store;

import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

public class StoreSteps extends AbstractSteps implements En{
	boolean notNull = false;
	@Autowired
	Store store;
	
	public StoreSteps() {


		Given("Admin wants to create a store with the following attributes", (DataTable storeDt) -> {
			testContext().reset();

			List<List<String>> storeData = storeDt.asLists(String.class);
			store = populateStore(storeData.get(1));

		});

		When("admin saves the new store {string}", (String testContext) -> {
			
			//      // AbstractSteps class makes the POST call and stores response in TestContext
			//      executePost(createStoreUrl);

			notNull = store.getName().isEmpty()? false:true;
		});

		/**
		 * This can be moved to a Class named 'CommonSteps.java so that it can be reused.
		 */
		Then("the save {string}", (String expectedResult) -> {
			assertTrue(notNull);
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
