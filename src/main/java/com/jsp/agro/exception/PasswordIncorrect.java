package com.jsp.agro.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordIncorrect extends RuntimeException {
	String msg="not found";
}
