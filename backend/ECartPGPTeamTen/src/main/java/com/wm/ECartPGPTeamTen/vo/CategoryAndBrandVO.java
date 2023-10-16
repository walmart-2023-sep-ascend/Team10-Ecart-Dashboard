package com.wm.ECartPGPTeamTen.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.wm.ECartPGPTeamTen.model.ProductsModel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Component
@Data
@Setter
@Getter
public class CategoryAndBrandVO implements Serializable {

	private static final long serialVersionUID = 1L;

	Map<String, List<ProductsModel>> brands;
	Map<String, List<ProductsModel>> categories;
}
