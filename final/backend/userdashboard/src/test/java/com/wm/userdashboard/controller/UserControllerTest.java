package com.wm.userdashboard.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wm.userdashboard.BaseTest;
import com.wm.userdashboard.exception.ECartException;
import com.wm.userdashboard.exception.ResourceNotFoundException;
import com.wm.userdashboard.model.UserModel;
import com.wm.userdashboard.service.UserService;
import com.wm.userdashboard.vo.UserDataResponseVO;
/**
 * @author s0k05sw
 * UserControllerTest to test UserController class and other dependent classes
 */
public class UserControllerTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserDataSuccess() throws ResourceNotFoundException, ECartException {
        // Arrange
        String emailId = "test@example.com";
        List<String> interests = List.of("Java", "Spring");
        List<String> favCategories = List.of("Books", "Electronics");
        List<String> recentSearches = List.of("Spring Boot", "REST API");

        UserModel mockUser = new UserModel();
        mockUser.setInterests(interests);
        mockUser.setFavoriteCategories(favCategories);
        mockUser.setRecentSearches(recentSearches);

        when(userService.findByEmail(emailId)).thenReturn(Optional.of(mockUser));

        // Act
        ResponseEntity<UserDataResponseVO> response = userController.getUserData(emailId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());

        UserDataResponseVO responseBody = response.getBody();
        assertEquals(interests, responseBody.getUserInterests());
        assertEquals(favCategories, responseBody.getFavoriteCategories());
        assertEquals(recentSearches, responseBody.getRecentSearches());

        // Logger statements
        logger.info("Test successful: Fetching user information for email id: {}", emailId);
    }

    @Test
    void testGetUserDataNotFound() throws ResourceNotFoundException, ECartException {
        // Arrange
        String emailId = "nonexistent@example.com";
        when(userService.findByEmail(emailId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> userController.getUserData(emailId));

        // Logger statements
        logger.info("Test successful: User not found for email: {}", emailId);
    }

    @Test
    void testGetUserDataException() throws ResourceNotFoundException, ECartException {
        // Arrange
        String emailId = "test@example.com";
        when(userService.findByEmail(emailId)).thenThrow(new RuntimeException("Some error occurred"));

        // Act and Assert
        assertThrows(ECartException.class, () -> userController.getUserData(emailId));

        // Logger statements
        logger.info("Test successful: Exception while fetching user information for email: {}", emailId);
    }
}
