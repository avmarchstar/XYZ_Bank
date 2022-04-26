package com.globalsqa.pages;

import com.globalsqa.pages.helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    //    @FindBy(css = "tr[class='ng-scope']")
    @FindBy(css = "tr[id^='anchor']")
    protected List<WebElement> transactions;
    @FindBy(css = "[ng-show='message']")
    protected WebElement withdrawAboveMsg;
    @FindBy(css = "[ng-show='right']")
    protected WebElement paginationRightBtn;

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

    public boolean firstTransactionDataContains(String param) {
        if (!transactionDate.isEmpty() && !transactions.isEmpty()) {
            return waitWebElement(transactions.get(0)).getText().contains(param);
        }
        return false;
    }

    public boolean lastTransactionDataContains(String param) {
        if (!transactions.isEmpty() && paginationRightBtn.isDisplayed()) {
            do {
                paginationRightBtn.click();
            } while (transactions.get(transactions.size() - 1).getText().isEmpty());
            return transactions.get(transactions.size() - 1).getText().contains(param);
        }
        return false;
    }

    public String dateTodayForDatePicker() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        return sdf.format(calendar.getTime());
    }

    public String dateTodayForTest() {
        return new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)
                .format(Calendar.getInstance().getTime());
    }

    public boolean checkWithdrawAboveMsg(Long balance, String moneyForTest) {

        String regexNumber = "[^\\D]";
        Pattern p = Pattern.compile(regexNumber);
        Matcher m = p.matcher(moneyForTest);
        if (m.matches() && balance < Long.parseLong(moneyForTest)) {
            return withdrawAboveMsg.getText().equals(MSG_WHISDRAW_ABOVE);
        }
        return true;
    }


}

