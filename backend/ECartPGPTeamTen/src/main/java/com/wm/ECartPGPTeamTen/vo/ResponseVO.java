package com.wm.ECartPGPTeamTen.vo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Component
@Data
@Setter
@Getter
public class ResponseVO {

	private Integer code;
	private Object  body;
	private String  message;
}
