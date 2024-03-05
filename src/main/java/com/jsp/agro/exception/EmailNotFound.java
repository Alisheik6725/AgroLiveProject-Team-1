package com.jsp.agro.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotFound extends RuntimeException{
	String msg="email not found";

}
