package com.jsp.agro.service;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.agro.dao.EquipmentDao;
import com.jsp.agro.dao.PaymentHistoryDao;
import com.jsp.agro.dao.RentalDao;
import com.jsp.agro.entity.Equipment;
import com.jsp.agro.entity.PaymentHistory;
import com.jsp.agro.entity.Rental;
import com.jsp.agro.exception.EquipmentNotFound;
import com.jsp.agro.util.ResponseStructure;
import com.jsp.agro.util.TimeCalculator;

@Service
public class RentalService {
	@Autowired
	private RentalDao rentalDao;
	@Autowired
	private Rental rental;
	@Autowired
	private EquipmentDao equipmentDao;
	@Autowired
	private PaymentHistory payment;
	@Autowired
	private PaymentHistoryDao paymentDao;
	
	
	DateTimeFormatter formatter = new DateTimeFormatterBuilder()
	        .appendPattern("yyyy-MM-dd[ HH:mm:ss]")
	        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
	        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
	        .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
	        .toFormatter();
	
	
	public ResponseEntity<ResponseStructure<Rental>> saveRental(String equipmentId,String startDateAndTime,String endDateAndTime){
		Equipment equipmentDb = equipmentDao.fetchEquipmentById(equipmentId);
		if(equipmentDb!=null) {
			rental.setEquipment(equipmentDb);
			LocalDateTime fromDateTime= LocalDateTime.parse(startDateAndTime,formatter);
	    	LocalDateTime toDateTime= LocalDateTime.parse(endDateAndTime,formatter);
	    	rental.setStartDataAndTime(fromDateTime);
	    	rental.setEndDataAndTime(toDateTime);
			long[] duration = TimeCalculator.getDuration(fromDateTime, toDateTime);
			double amount=((duration[0]*365)*24+(duration[1]*30)*24+duration[2]*24+duration[3])*equipmentDb.getCostPerHrs();
			System.out.println("year "+(duration[0]*365)*24);
			System.out.println("month "+(duration[1]*30)*24);
			System.out.println("day "+duration[2]*24);
			System.out.println("hours "+duration[3]);
				
			payment.setAmount(amount);
			PaymentHistory paymentDb = paymentDao.savePayment(payment);
			rental.setPaymentHistory(paymentDb);
			
			Rental rentalDb = rentalDao.saveRental(rental);
			
			ResponseStructure<Rental> response=new ResponseStructure<Rental>();
			response.setData(rentalDb);
			response.setMsg("saved successfully........!");
			response.setStatus(HttpStatus.CONTINUE.value());
			return new ResponseEntity<ResponseStructure<Rental>>(response,HttpStatus.CONTINUE);
			
		}
		throw new EquipmentNotFound("equipment not found for id : "+equipmentId);
		
	}

}
