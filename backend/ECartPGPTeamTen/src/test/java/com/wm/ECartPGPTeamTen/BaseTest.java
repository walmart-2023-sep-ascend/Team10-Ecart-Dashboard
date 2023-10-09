package com.wm.ECartPGPTeamTen;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.Suite;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@SpringBootTest
@ExtendWith(SpringExtension.class)
@Suite.SuiteClasses({
    UserServiceTest.class,
    ProductServicesTest.class
})
//@ActiveProfiles("dev")
//@DataMongoTest
public abstract class BaseTest {

}
