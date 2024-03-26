package com.jsp.agro.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

import com.jsp.agro.util.MyGenerator;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_seq")
	@GenericGenerator(name = "rental_seq", strategy = "com.jsp.agro.util.MyGenerator", parameters = {
			@Parameter(name = MyGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = MyGenerator.VALUE_PREFIX_PARAMETER, value = "rental"),
			@Parameter(name = MyGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String id;
	private LocalDateTime startDataAndTime;
	private LocalDateTime endDataAndTime;
	@ManyToOne
	private Equipment equipment;
	@OneToOne
	private PaymentHistory paymentHistory;
}
