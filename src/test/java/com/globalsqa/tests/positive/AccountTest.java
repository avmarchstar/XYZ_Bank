package com.globalsqa.tests.positive;

import com.globalsqa.pages.AccountPage;
import com.globalsqa.pages.HomePage;
import helpers.BaseTest;
import helpers.Dp;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountTest extends BaseTest {

    long moneyInAccount;
    long resultValue;
    HomePage homePage;
    AccountPage accountPage;

    @Test(dataProvider = "customerMoneyData", dataProviderClass = Dp.class)
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
        accountPage.getTransactionDateFrom().sendKeys(accountPage.dateForTest());

//        Check operation's data: amount of money and type of operation
        Assert.assertTrue(accountPage.getFirstTransaction().getText().contains(AccountPage.OPER_TYPE_CREDIT));
        Assert.assertTrue(accountPage.getFirstTransaction().getText().contains(moneyForTest));

        Assert.assertTrue(accountPage.getLastTransaction().getText().contains(AccountPage.OPER_TYPE_DEBIT));
        Assert.assertTrue(accountPage.getLastTransaction().getText().contains(moneyForTest));

//          Check logout button
        accountPage.getLogoutBtn().click();

        Assert.assertTrue(homePage.getSelector().isDisplayed());


    }

}
