package com.nicusa.testing.tests.geb

import com.nicusa.testing.tests.config.TestConfiguration
import com.nicusa.testing.tests.rules.ClearDatabaseBeforeAndAfterTest
import geb.Browser
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import pages.DashboardPage
import pages.FacebookLoginPage

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(classes = [TestConfiguration])
@ActiveProfiles("mysql")
class SignInTest {


    @Rule
    @Autowired
    public ClearDatabaseBeforeAndAfterTest clearDatabaseBeforeAndAfterTest;

    @Test
    public void testSignIn() {
        Browser browser = new Browser();
        browser.drive {
            to DashboardPage
            facebookLoginButton.click()
            at FacebookLoginPage
        }
    }

}
