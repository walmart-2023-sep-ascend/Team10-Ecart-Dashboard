package com.wm.ECartPGPTeamTen.service;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.ECartPGPTeamTen.Dto.UpcomingPromotionDTO;
import com.wm.ECartPGPTeamTen.dao.repository.UpcomingPromotionRepository;
import com.wm.ECartPGPTeamTen.exception.ECartException;
import com.wm.ECartPGPTeamTen.model.PromotionsModel;

@Service
public class UpcomingPromotionService {

	private static final Logger logger = LoggerFactory.getLogger(UpcomingPromotionService.class);
	private final UpcomingPromotionRepository promotionRepository;

	
    @Autowired
    public UpcomingPromotionService(UpcomingPromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }
    
	/**
	 * Fetches Upcoming promotions
	 * 
	 * @param CurrentDate,Status Active
	 * @return
	 * @throws ECartException
	 */
    
    public List<UpcomingPromotionDTO> getActivePromotions() throws ECartException {
        try {
        	
            Date currentDate = new Date();

            // Calculate the end date as 30 days from the current date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.DATE, 30);
            Date endDate = calendar.getTime();

            List<PromotionsModel> activePromotions = promotionRepository.findActivePromotions(currentDate, endDate, "active");

            System.out.println("Date Passed from service Layer: " + currentDate);
            System.out.println("Active Promotions: " + activePromotions);
            

            if (activePromotions.isEmpty()) {
                System.out.println("No active promotions found.");
                return Collections.emptyList();
            }

            //System.out.println("Date Passed from service Layer: " + currentDate);
            //System.out.println("Active Promotions: " + activePromotions);
            
            logger.info("Date Passed from service Layer:" + currentDate);

            for (PromotionsModel promotionsModel : activePromotions) {
            	 logger.info("Promotion ID: " + promotionsModel.getPromotionID());
            	 logger.info("Promotion Name: " + promotionsModel.promotionDescription());
            	 logger.info("Value change: " + promotionsModel.getValueChange()); // Use getValueChange directly
      
            }

            return activePromotions.stream()
                    .map(promotion -> new UpcomingPromotionDTO(promotion.getPromotionID(), promotion.promotionDescription(), promotion.getValueChange())) // Use getValueChange directly
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error occurred " + e);
            throw new ECartException("Internal server Error");
        }
    }



    
    
}
