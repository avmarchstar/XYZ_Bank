package com.globalsqa.tests.negative;

import com.globalsqa.pages.AccountPage;
import com.globalsqa.pages.HomePage;
import com.globalsqa.pages.helpers.BaseTest;
import com.globalsqa.pages.helpers.Dp;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountTest extends BaseTest {

    long moneyInAccount;
    HomePage homePage;
    AccountPage accountPage;

    @Test(dataProvider = "customerMoneyDataNegative", dataProviderClass = Dp.class)
    public void workAccountTest(String customerName, String accountNumber, String moneyForTest) {

        homePage = new HomePage(driver);

//          Withdraw money from account
        accountPage = homePage.getAccountPage(driver, customerName);
        accountPage.getWithdrawlBtn().click();
        accountPage.getSelect().selectByVisibleText(accountNumber);
        moneyInAccount = Long.parseLong(accountPage.getBalance());
        accountPage.getAccountInput().sendKeys(Keys.chord(moneyForTest, Keys.ENTER));

        Assert.assertTrue(accountPage.checkWithdrawAboveMsg(moneyInAccount, moneyForTest));
        Assert.assertEquals(moneyInAccount, Long.parseLong(accountPage.getBalance()));
        accountPage.getHomeBtn().click();

//          Transactions check
        homePage = new HomePage(driver);
        accountPage = homePage.getAccountPage(driver, customerName);
        accountPage.getSelect().selectByVisibleText(accountNumber);
        accountPage.getTransactionsBtn().click();

        Assert.assertFalse(accountPage.lastTransactionDataContains(moneyForTest));
        Assert.assertFalse(accountPage.lastTransactionDataContains(accountPage.dateTodayForTest()));
    }

}
