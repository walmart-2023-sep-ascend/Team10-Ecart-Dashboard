package com.wm.ECartPGPTeamTen;

import static com.wm.ECartPGPTeamTen.util.ECartUtil.INTERNAL_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
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
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;

import com.wm.ECartPGPTeamTen.controller.UserController;
import com.wm.ECartPGPTeamTen.dao.UserDao;
import com.wm.ECartPGPTeamTen.dao.repository.UserRepository;
import com.wm.ECartPGPTeamTen.exception.ECartException;
import com.wm.ECartPGPTeamTen.model.UserModel;
import com.wm.ECartPGPTeamTen.service.MessageService;
import com.wm.ECartPGPTeamTen.service.UserService;

public class UserServiceTest extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

	@InjectMocks
	MessageService messageService;

	@InjectMocks
	UserService userService;

	@Mock
	// @InjectMocks
	UserDao userDao;

	@Mock
	UserRepository userRepository;

	@Mock
	// @InjectMocks
	MongoTemplate mongoTemplate;

	@InjectMocks
	UserController userController;

	@BeforeEach
	void initialize() {
		userService = new UserService(userRepository, mongoTemplate);
		userDao = new UserDao(mongoTemplate);
		userController = new UserController(userService);
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getUserDetailsTestException() throws Exception {
		logger.debug("Test case : getUserDetailsTestException");

		List<UserModel> returnList = new ArrayList<UserModel>();
		UserModel model = new UserModel();
		model.setId(1);
		model.set_id("test");
		returnList.add(model);
		logger.debug(model.toString());
		when(userDao.findById(anyInt())).thenThrow(new Exception("Issue"));

		Throwable exception = assertThrows(ECartException.class, () -> userController.getEmployeeById(1));
		assertEquals(INTERNAL_ERROR + " :: " + 1, exception.getMessage());

	}

	@Test
	void getUserDetailsTestRecordNotFoundException() throws Exception {
		logger.debug("Test case : getUserDetailsTestRecordNotFoundException");

		List<UserModel> returnList = new ArrayList<UserModel>();

		when(userDao.findById(anyInt())).thenReturn(returnList);

		Throwable exception = assertThrows(ECartException.class, () -> userController.getEmployeeById(1));
		assertEquals(INTERNAL_ERROR + " :: " + 1, exception.getMessage());
	}

	@Test
	void getEmployeeByIdTest() throws Exception {

		logger.debug("getEmployeeByIdTest ");

		List<UserModel> returnList = new ArrayList<UserModel>();
		UserModel model = new UserModel();
		model.setId(1);
		model.set_id("test");
		returnList.add(model);
		when(userDao.findById(anyInt())).thenReturn(returnList);

		ResponseEntity<UserModel> users = userController.getEmployeeById(1);
		logger.info("list " + users);
		assertTrue(users.getStatusCode().is2xxSuccessful());
	}

	@Test
	void getUserDetailsEmailTest() throws Exception {

		logger.debug("Test case : getUserDetailsEmailTest");

		UserModel model = new UserModel();
		model.setId(1);
		model.set_id("test");
		Optional<UserModel> returnList = Optional.of(model);
		when(userRepository.findByEmail(anyString())).thenReturn(returnList);

		Optional<UserModel> users = userService.getUserDetailsEmail("email id");
		logger.info("list " + users);
		assertTrue(users.isPresent());
	}

	@Test
	void getUserDetailsEmailTestException() throws Exception {
		logger.debug("Test case : getUserDetailsEmailTestException");

		when(userRepository.findByEmail(anyString())).thenThrow(new Exception("Issue"));

		Throwable exception = assertThrows(ECartException.class, () -> userService.getUserDetailsEmail("test mail"));
		assertEquals(INTERNAL_ERROR + " :: test mail" , exception.getMessage());

	}
}
