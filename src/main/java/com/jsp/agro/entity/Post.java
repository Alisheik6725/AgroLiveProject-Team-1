package com.jsp.agro.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

import com.jsp.agro.util.MyGenerator;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
	@GenericGenerator(name = "post_seq", strategy = "com.jsp.agro.util.MyGenerator", parameters = {
			@Parameter(name = MyGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = MyGenerator.VALUE_PREFIX_PARAMETER, value = "post"),
			@Parameter(name = MyGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String id;
	@OneToOne(cascade=CascadeType.ALL)
	private Image image;
	private int likes;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Comment> comment;
	private LocalDateTime date;
	private String caption;
	private String location;

}
