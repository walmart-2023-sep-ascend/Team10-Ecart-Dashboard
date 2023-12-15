package com.ascend.apigateway.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements Serializable {


	private static final long serialVersionUID = 1L;
	private Integer errorCode;
	private String error;
	private String description;
	
	
	
	
}
