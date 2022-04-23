package com.globalsqa.pages;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AccountPage extends BasePage {

    @FindBy(id = "accountSelect")
    protected WebElement accountSelect;
    @FindBy(css = "[ng-class='btnClass1']")
    protected WebElement transactionsBtn;
    @FindBy(css = "[ng-class='btnClass2']")
    protected WebElement depositBtn;
    @FindBy(css = "[ng-class='btnClass3']")
    protected WebElement withdrawlBtn;
    @FindBy(className = "fontBig")
    protected WebElement clientName;
    @FindBy(className = "ng-binding")
    protected List<WebElement> accountParameters;
    @FindBy(css = "input[type='number']")
    protected WebElement accountInput;
    @FindBy(css = "[type='submit']")
    protected WebElement moneySubmitBtn;
    @FindBy(css = "[type='datetime-local']")
    protected List<WebElement> transactionDate;
    @FindBy(css = "tr[class='ng-scope']")
    protected List<WebElement> transactions;

    protected String balance;
    protected String currencyName;


    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getAccountSelect() {
        return accountSelect;
    }

    public Select getSelect() {
        return new Select(getAccountSelect());
    }

    public WebElement getDepositBtn() {
        return waitWebElement(depositBtn);
    }

    public WebElement getWithdrawlBtn() {
        return waitWebElement(withdrawlBtn);
    }

    public String getClientNameText() {
        return waitWebElement(clientName).getText();
    }

    public String getBalance() {
        return balance = accountParameters.get(2).getText();
    }

    public String getCurrencyName() {
        return currencyName = accountParameters.get(3).getText();
    }

    public WebElement getAccountInput() {
        return waitWebElement(accountInput);
    }

    public WebElement getMoneySubmitBtn() {
        return waitWebElement(moneySubmitBtn);
    }

    public WebElement getTransactionsBtn() {
        return waitWebElement(transactionsBtn);
    }

    public WebElement getTransactionDateFrom() {
        return waitWebElement(transactionDate.get(0));
    }

    public WebElement getFirstTransaction() {
        return waitWebElement(transactions.get(0));
    }

    public WebElement getLastTransaction() {
        return waitWebElement(transactions.get(1));
    }


}
