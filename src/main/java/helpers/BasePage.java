package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

public class BasePage {

    public static final String CUSTOMER_NAME = "Hermoine Granger";
    public static final String OPER_TYPE_CREDIT = "Credit";
    public static final String OPER_TYPE_DEBIT = "Debit";

    protected WebDriver driver;
    @FindBy(css = ".btn.home")
    protected WebElement homeBtn;
    @FindBy(css = ".btn.logout")
    protected WebElement logoutBtn;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getHomeBtn() {
        return waitWebElement(homeBtn);
    }

    public WebElement getLogoutBtn() {
        return waitWebElement(logoutBtn);
    }

    protected WebElement waitWebElement(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public String dateForTest() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        return sdf.format(calendar.getTime());
    }

}
