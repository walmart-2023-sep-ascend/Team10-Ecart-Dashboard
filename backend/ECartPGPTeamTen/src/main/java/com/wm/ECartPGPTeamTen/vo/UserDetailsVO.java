package com.wm.ECartPGPTeamTen.vo;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Component
@Data
@Setter
@Getter
public class UserDetailsVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	
	private String passcode;

}
