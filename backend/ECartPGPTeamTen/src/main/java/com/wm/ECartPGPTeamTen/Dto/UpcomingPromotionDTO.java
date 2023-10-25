package com.wm.ECartPGPTeamTen.Dto;

public class UpcomingPromotionDTO {
	
	   private Integer id;
	    private String name;
	    private String value;

	    public UpcomingPromotionDTO(Integer id, String name,String value) {
	        this.id = id;
	        this.name = name;
	        this.value = value;
	    }

	    public Integer getId() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }

	    public String getValue() {
	        return value;
	    }
}
