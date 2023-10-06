package com.wm.ECartPGPTeamTen.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * r0m09yu
 */

@Data
@Setter
@Getter
public class AddressModel{
	private String  city;
	private String street;
	private String number;
	private String zipcode;
}
