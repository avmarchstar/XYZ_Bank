package com.globalsqa.tests.positive;

import com.globalsqa.pages.AccountPage;
import com.globalsqa.pages.HomePage;
import com.globalsqa.pages.ManagerPage;
import com.globalsqa.pages.helpers.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {


    @Test(description = "Customer Login")
    public void testCustomerLogin() {

        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = new AccountPage(driver);

        homePage.getCustomerLoginBtn().click();
        homePage.setSelector(HomePage.CUSTOMER_NAME);
        homePage.getLoginBtn().click();

//          Comparison of input username and resulting username
        Assert.assertEquals(HomePage.CUSTOMER_NAME, accountPage.getClientNameText());
//          Presence of an element on the landing page
        Assert.assertTrue(accountPage.getLogoutBtn().isDisplayed());

    }

    @Test(description = "Bank manager Login")
    public void testBankManagerLogin() {

        HomePage homePage = new HomePage(driver);
        homePage.getBankManagerLoginBtn().click();
        ManagerPage managerPage = new ManagerPage(driver);
        Assert.assertTrue(managerPage.getOpenAccount().isDisplayed());
        Assert.assertTrue(driver.getCurrentUrl().contains("manager"));

    }

}
