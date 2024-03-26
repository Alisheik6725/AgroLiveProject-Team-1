package com.jsp.agroLiveProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.jsp.agroLiveProject.entity.Equipment;
import com.jsp.agroLiveProject.entity.Rental;
import com.jsp.agroLiveProject.service.RentalService;
import com.jsp.agroLiveProject.util.ResponseStructure;

@RestController
public class RentalController {

	@Autowired
	private RentalService rService;
	
	@PostMapping("/saveRental")
	public ResponseEntity<ResponseStructure<Rental>> saveRental(@RequestParam int eid, @RequestBody Rental r ) {
		return rService.saveRental(eid,r);
		
	}
}
