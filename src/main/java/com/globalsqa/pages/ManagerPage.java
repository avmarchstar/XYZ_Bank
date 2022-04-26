package com.globalsqa.pages;

import com.globalsqa.pages.helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManagerPage extends BasePage {

    @FindBy(css = "button[ng-class='btnClass2']")
    protected WebElement openAccount;

    public ManagerPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getOpenAccount() {
        return waitWebElement(openAccount);
    }
}


