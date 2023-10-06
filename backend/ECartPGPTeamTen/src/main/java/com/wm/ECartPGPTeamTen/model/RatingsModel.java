package com.wm.ECartPGPTeamTen.model;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * r0m09yu
 */


@Data
@Setter
@Getter
public class RatingsModel {
	private Integer averageRatings;
	private Integer numberOfReviews;
	private List<UserComments> comments;
}