package com.wm.userdashboard;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.Suite;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wm.userdashboard.controller.UserControllerTest;
import com.wm.userdashboard.controller.WishlistControllerTest;

//@SpringBootTest
@ExtendWith(SpringExtension.class)
@Suite.SuiteClasses({
    UserControllerTest.class,
    WishlistControllerTest.class
})
//@ActiveProfiles("dev")
//@DataMongoTest
public abstract class BaseTest {

}
