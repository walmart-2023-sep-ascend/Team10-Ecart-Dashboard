package com.wm.ECartPGPTeamTen.vo;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EcartJwtResponse implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(EcartJwtResponse.class);
	
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final String email;
	private final Integer id;

	public EcartJwtResponse(String jwttoken,Integer id,String email) {
		this.jwttoken = jwttoken;
		this.id = id;
		this.email = email;
		logger.info("id :"+this.id+" - email : "+this.email);
	}

	public String getToken() {
		return this.jwttoken;
	}
}