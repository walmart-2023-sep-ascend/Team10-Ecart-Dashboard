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
public class UserComments {
	private String userId;
	private String comment;
	private Integer rate;
}