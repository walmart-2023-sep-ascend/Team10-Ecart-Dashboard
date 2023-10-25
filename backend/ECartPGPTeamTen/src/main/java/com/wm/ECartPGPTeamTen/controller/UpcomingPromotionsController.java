package com.wm.ECartPGPTeamTen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wm.ECartPGPTeamTen.Dto.UpcomingPromotionDTO;
import com.wm.ECartPGPTeamTen.service.UpcomingPromotionService;
import com.wm.ECartPGPTeamTen.model.PromotionsModel;

@RestController
@RequestMapping("/promotions")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UpcomingPromotionsController {
	
    @Autowired
    private UpcomingPromotionService promotionService;

    @GetMapping("/actives")
    public ResponseEntity<List<UpcomingPromotionDTO>> getActivePromotions() {
        try {
            List<UpcomingPromotionDTO> activePromotions = promotionService.getActivePromotions();
            return ResponseEntity.ok(activePromotions);
        } catch (Exception e) {
            // Handle the exception and log it
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    } 
    
    
}