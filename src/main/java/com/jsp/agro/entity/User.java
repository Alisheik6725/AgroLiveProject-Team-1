package com.jsp.agro.entity;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.jsp.agro.enums.UserType;
import com.jsp.agro.util.MyGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@GenericGenerator(name = "user_seq", strategy = "com.jsp.agro.util.MyGenerator", parameters = {
			@Parameter(name = MyGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = MyGenerator.VALUE_PREFIX_PARAMETER, value = "user"),
			@Parameter(name = MyGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String id;
	@NotNull(message="name is mandatory")
	@NotBlank(message="name cannot be blank")
	private String firstName;
	@NotNull(message="lastname is mandatory")
	@NotBlank(message="lastname cannot be blank")
	private String lastName;
	@Column(unique=true)
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email ")
	private String email;
	@NotBlank(message = "Password is required")
	@Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
	@Pattern(regexp =  "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "must contain at least one letter, one number, one special character")
	private String pwd;
	@Min(value= 6000000000l, message = " phone number must be valid" )
	@Max(value= 9999999999l, message = " phone number must be valid" )
	private long phno;
	private String gender;
	private int age;
	@Enumerated(EnumType.STRING)
	private UserType type;
	@OneToOne(cascade=CascadeType.ALL)
	private Address address;
	@OneToOne(cascade=CascadeType.ALL)
	private Image image;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Post> post;
	
	
	

}
