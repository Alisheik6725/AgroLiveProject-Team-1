package com.jsp.agro.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStructure<T> {
	private String msg;
	private int Status;
	private T data;

}
