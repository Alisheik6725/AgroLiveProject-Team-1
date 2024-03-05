package com.jsp.agro.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageNotFound extends RuntimeException {
	String Msg="not found";

}
