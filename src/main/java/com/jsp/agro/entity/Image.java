package com.jsp.agro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

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
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "img_seq")
	@GenericGenerator(name = "img_seq", strategy = "com.jsp.agro.util.MyGenerator", parameters = {
			@Parameter(name = MyGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = MyGenerator.VALUE_PREFIX_PARAMETER, value = "img"),
			@Parameter(name = MyGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String id;
	private String description;
	@Lob
	@Column(name="picture",columnDefinition="LONGBLOB",length=1000000)
	private byte[] picture;

}
