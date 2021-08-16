
package com.rvy.store.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rvy.store.app.StoreDemoApplication;
import com.rvy.store.entity.Taxation;

import com.rvy.store.service.TaxationService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT, classes = {StoreDemoApplication.class})
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class  TaxationControllerTest{
    @LocalServerPort
    private int randomServerPort;
    
//    @Autowired
//    private TestRestTemplate testRestTemplate;
    
    @MockBean
	private TaxationService taxationService;
    
    private Taxation taxMock1;
	private Taxation taxMock2;


     
    @Test
    public void testGetTaxById() throws URISyntaxException 
    {
    	
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/rvy/um/v1/tax/1";
        URI uri = new URI(baseUrl);
     
        ResponseEntity<String> result = testRestTemplate.getForEntity(uri, String.class);
         
        //Verify request succeed
        assertEquals(200, result.getStatusCodeValue());
        System.out.println(result.getBody());
 
    } 
	
//	@Test
//	public void addTaxationTest() throws TaxationException, URISyntaxException  {
//	    taxMock1 = new Taxation(null,1100010010,"GST",18.0f,LocalDate.of(2021, 1, 12),null);
//		when(taxationService.addTax(taxMock1)).thenReturn(taxMock1);
//	    System.out.println(taxMock1.getTaxId());
//
//        final String baseUrl = "http://localhost:" + randomServerPort + "/rvy/um/v1/tax";
//        URI uri = new URI(baseUrl);
//        String id = String.valueOf(taxMock1.getTaxId());
//        URI uri2 = new URI("http://localhost:" + randomServerPort + "/rvy/um/v1/tax/"+id);
//        TestRestTemplate testRestTemplate = new TestRestTemplate();
//        testRestTemplate.postForEntity(uri, taxMock1, Taxation.class);
//        ResponseEntity<Taxation> result=testRestTemplate.getForEntity(uri2,Taxation.class);
//      ResponseEntity<String> result = testRestTemplate.getForEntity(uri, String.class);
//
//		assertNotNull(result);
//		assertNotNull(result.getBody());
//		//System.out.println(result.getBody());
//	}


}

