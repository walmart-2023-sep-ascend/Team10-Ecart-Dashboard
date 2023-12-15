package com.wm.userdashboard.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import com.wm.userdashboard.model.ProductsWishListModel;
import com.wm.userdashboard.model.WishListModel;
import com.wm.userdashboard.service.WishlistService;
/**
 * @author s0k05sw
 * WishlistControllerTest to test WishlistController class and dependent classes
 */
public class WishlistControllerTest extends BaseTest{

    private static final Logger logger = LoggerFactory.getLogger(WishlistControllerTest.class);

    @Mock
    private WishlistService wishlistService;

    @InjectMocks
    private WishlistController wishlistController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserWishlistSuccess() throws ResourceNotFoundException, ECartException {
        // Arrange...
        String userEmail = "test@example.com";
        List<ProductsWishListModel> products = new ArrayList<>();
        products.add(new ProductsWishListModel("1", "available"));
        products.add(new ProductsWishListModel("2", "available"));

        WishListModel mockWishlist = new WishListModel();
        mockWishlist.setProducts(products);

        when(wishlistService.getUserWishlist(userEmail)).thenReturn(Optional.of(mockWishlist));

        // Act...
        ResponseEntity<List<String>> response = wishlistController.getUserWishlist(userEmail);

        // Assert...
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<String> productIdList = response.getBody();
        assertEquals(2, productIdList.size());
        assertEquals("1", productIdList.get(0));
        assertEquals("2", productIdList.get(1));

        // Logger statements
        logger.info("Test successful: Fetching wishlist for user with email: {}", userEmail);
    }

    @Test
    void testGetUserWishlistNotFound() throws ResourceNotFoundException, ECartException {
        // Arrange...
        String userEmail = "nonexistent@example.com";
        when(wishlistService.getUserWishlist(userEmail)).thenReturn(Optional.empty());

        // Act and Assert...
        assertThrows(ResourceNotFoundException.class, () -> wishlistController.getUserWishlist(userEmail));

        // Logger statements
        logger.info("Test successful: Wishlist not found for user with email: {}", userEmail);
    }

    @Test
    void testGetUserWishlistException() throws ResourceNotFoundException, ECartException {
        // Arrange...
        String userEmail = "test@example.com";
        when(wishlistService.getUserWishlist(userEmail)).thenThrow(new RuntimeException("Some error occurred"));

        // Act and Assert...
        assertThrows(ECartException.class, () -> wishlistController.getUserWishlist(userEmail));

        // Logger statements
        logger.info("Test successful: Exception while fetching wishlist for user with email: {}", userEmail);
    }
}
