package com.wm.ECartPGPTeamTen.service;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.ECartPGPTeamTene.Dto.UpcomingPromotionDTO;
import com.wm.ECartPGPTeamTen.dao.repository.UpcomingPromotionRepository;
import com.wm.ECartPGPTeamTen.model.PromotionsModel;
import com.wm.ECartPGPTeamTen.exception.ECartException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

            List<Promotion> activePromotions = promotionRepository.findActivePromotions(currentDate, endDate, "active");

            System.out.println("Date Passed from service Layer: " + currentDate);
            System.out.println("Active Promotions: " + activePromotions);
            

            if (activePromotions.isEmpty()) {
                System.out.println("No active promotions found.");
                return Collections.emptyList();
            }

            //System.out.println("Date Passed from service Layer: " + currentDate);
            //System.out.println("Active Promotions: " + activePromotions);
            
            logger.info("Date Passed from service Layer:" + currentDate);

            for (PromotionsModel PromotionsModel : activePromotions) {
            	 logger.info("Promotion ID: " + PromotionsModel.getPromotionID());
            	 logger.info("Promotion Name: " + PromotionsModel.promotionDescription());
            	 logger.info("Value change: " + PromotionsModel.getValueChange()); // Use getValueChange directly
      
            }

            return activePromotions.stream()
                    .map(promotion -> new UpcomingPromotionDTO(PromotionsModel.getPromotionID(), PromotionsModel.promotionDescription(), promotion.getValueChange())) // Use getValueChange directly
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error occurred " + e);
            throw new ECartException("Internal server Error");
        }
    }



    
    
}
