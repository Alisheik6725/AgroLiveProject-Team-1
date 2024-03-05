package com.jsp.agro.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Image {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String description;
	private byte[] picture;

}
