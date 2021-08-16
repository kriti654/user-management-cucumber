package com.rvy.store.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.rvy.store.entity.Region;
import com.rvy.store.exceptions.RegionException;
import com.rvy.store.service.IRegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
@Api(tags = "Region Controller")
public class RegionController {
	@Autowired
	private IRegionService regionService;
	
	
	@ApiOperation(value = "Add Region",
			consumes = "Region object",
			produces = "Region object",
			response = Region.class,
			notes = "http://localhost:8081/rvy/um/v1/region"
			)
	@PostMapping("/region")
	public ResponseEntity<Region> addRegion(@Valid @RequestBody Region region,
			BindingResult bindingResult) {
		try {
		if(bindingResult.hasErrors()) {
			throw new RegionException(bindingResult.getAllErrors().toString());
		}
	      return ResponseEntity.ok().body(regionService.addRegion(region));	
	   } catch(RegionException e) {
		   throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
				   e.getMessage());
	   }
	}

}
