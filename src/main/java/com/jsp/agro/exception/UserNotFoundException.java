package com.jsp.agro.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserNotFoundException extends RuntimeException {
	
	 String msg="not found";

}
