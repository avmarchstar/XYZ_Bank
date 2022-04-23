package com.globalsqa.pages;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "[ng-click='customer()']")
    protected WebElement customerLoginBtn;
    @FindBy(css = "[ng-click='manager()']")
    protected WebElement bankManagerLoginBtn;
    @FindBy(id = "userSelect")
    protected WebElement selector;
    @FindBy(css = ".btn.btn-default[type='submit']")
    protected WebElement loginBtn;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getCustomerLoginBtn() {
        return waitWebElement(customerLoginBtn);
    }

    public void setSelector(String customerName) {
        waitWebElement(selector).sendKeys(customerName);
    }

    public WebElement getSelector() {
        return selector;
    }

    public WebElement getLoginBtn() {
        return waitWebElement(loginBtn);
    }

    public WebElement getBankManagerLoginBtn() {
        return waitWebElement(bankManagerLoginBtn);
    }

    public AccountPage getAccountPage(WebDriver driver, String customerName) {
        getCustomerLoginBtn().click();
        setSelector(customerName);
        getLoginBtn().click();
        return new AccountPage(driver);
    }

}
