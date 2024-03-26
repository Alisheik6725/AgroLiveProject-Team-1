package com.jsp.agro.controller;


import org.springframework.beans.factory.annotation.Autowired;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro.entity.Rental;
import com.jsp.agro.service.RentalService;
import com.jsp.agro.util.ResponseStructure;
import com.jsp.agro.util.TimeCalculator;

@RestController
public class RentalController {
	@Autowired
	private RentalService rentalService;
	

	@PostMapping("/saveRental")
	public ResponseEntity<ResponseStructure<Rental>> saveRental(@RequestParam String equipmentId ,@RequestParam String  startDateAndTime,@RequestParam String  endDateAndTime){
		
		  return rentalService.saveRental(equipmentId,startDateAndTime, endDateAndTime);
}
}