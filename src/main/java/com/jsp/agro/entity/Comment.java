package com.jsp.agro.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String msg;
	@OneToOne(cascade=CascadeType.ALL)
	private User user;
	

}
