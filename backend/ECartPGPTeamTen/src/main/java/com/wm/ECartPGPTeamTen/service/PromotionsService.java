package com.wm.ECartPGPTeamTen.service;

import static com.wm.ECartPGPTeamTen.util.ECartUtil.INTERNAL_ERROR;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.ECartPGPTeamTen.dao.PromotionsDao;
import com.wm.ECartPGPTeamTen.exception.ECartException;
import com.wm.ECartPGPTeamTen.model.PromotionsModel;

/**
 * r0m09yu
 */

@Service
public class PromotionsService {

	private static final Logger logger = LoggerFactory.getLogger(PromotionsService.class);

	@Autowired
	PromotionsDao promotionsDao;

	public List<PromotionsModel> getActivePromotinos() throws ECartException {
		try {
			logger.debug("Getting active promotions");

			LocalDate date = LocalDate.now();
			// date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			List<PromotionsModel> list = promotionsDao.getActivePromotinos(date);
			logger.debug("After getting promotions");

			return list;
		} catch (Exception e) {
			logger.error("Error occured " + e);
			throw new ECartException(INTERNAL_ERROR);
		}
	}

}
