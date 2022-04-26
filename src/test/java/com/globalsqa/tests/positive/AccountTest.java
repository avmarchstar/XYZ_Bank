package com.globalsqa.tests.positive;

import com.globalsqa.pages.AccountPage;
import com.globalsqa.pages.HomePage;
import com.globalsqa.pages.helpers.BaseTest;
import com.globalsqa.pages.helpers.Dp;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountTest extends BaseTest {

    long moneyInAccount;
    long resultValue;
    HomePage homePage;
    AccountPage accountPage;

    @Test(dataProvider = "customerMoneyDataPositive", dataProviderClass = Dp.class)
    public void workAccountTest(String customerName, String accountNumber, String moneyForTest, String currencyName) {

        homePage = new HomePage(driver);

//          Add money to account
        accountPage = homePage.getAccountPage(driver, customerName);
        accountPage.getSelect().selectByVisibleText(accountNumber);  //choose account
        accountPage.getDepositBtn().click();                        //go to deposit section
        moneyInAccount = Long.parseLong(accountPage.getBalance());
        accountPage.getAccountInput().sendKeys(moneyForTest);
        resultValue = Long.parseLong(accountPage.getBalance()) + Long.parseLong(moneyForTest);
        accountPage.getMoneySubmitBtn().click();

        Assert.assertEquals(currencyName, accountPage.getCurrencyName()); // Check name of currency
        Assert.assertEquals(resultValue, Long.valueOf(accountPage.getBalance()).longValue()); // Check result after addition
        accountPage.getHomeBtn().click();

//          Withdraw money from account
        accountPage = homePage.getAccountPage(driver, customerName);
        accountPage.getWithdrawlBtn().click();
        accountPage.getSelect().selectByVisibleText(accountNumber);
        moneyInAccount = Long.parseLong(accountPage.getBalance());
        resultValue = Long.parseLong(accountPage.getBalance()) - Long.parseLong(moneyForTest);
        accountPage.getAccountInput().sendKeys(Keys.chord(moneyForTest, Keys.ENTER));

        Assert.assertEquals(currencyName, accountPage.getCurrencyName()); // Check name of currency
        Assert.assertEquals(resultValue, Long.valueOf(accountPage.getBalance()).longValue()); // Check result after substraction
        accountPage.getHomeBtn().click();

//          Transactions check
        HomePage homePage = new HomePage(driver);
        accountPage = homePage.getAccountPage(driver, customerName);
        accountPage.getSelect().selectByVisibleText(accountNumber);
        accountPage.getTransactionsBtn().click();
        accountPage.getTransactionDateFrom().sendKeys(accountPage.dateTodayForDatePicker());

//        Check operation's data: amount of money and type of operation
        Assert.assertTrue(accountPage.firstTransactionDataContains(AccountPage.OPER_TYPE_CREDIT));
        Assert.assertTrue(accountPage.firstTransactionDataContains(moneyForTest));
        Assert.assertTrue(accountPage.firstTransactionDataContains(accountPage.dateTodayForTest()));

        Assert.assertTrue(accountPage.lastTransactionDataContains(AccountPage.OPER_TYPE_DEBIT));
        Assert.assertTrue(accountPage.lastTransactionDataContains(moneyForTest));
        Assert.assertTrue(accountPage.lastTransactionDataContains(accountPage.dateTodayForTest()));

//          Check logout button
        accountPage.getLogoutBtn().click();
        Assert.assertTrue(homePage.getSelector().isDisplayed());


    }

}
