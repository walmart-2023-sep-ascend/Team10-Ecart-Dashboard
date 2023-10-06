package com.wm.ECartPGPTeamTen.vo;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Component
@Data
@Setter
@Getter
public class CategoryAndBrandVO implements Serializable {

	private static final long serialVersionUID = 1L;

	List<String> brands;
	List<String> categories;
}
