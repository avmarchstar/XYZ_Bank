package helpers;

import org.testng.annotations.DataProvider;

public class Dp {

    @DataProvider(name = "customerMoneyData")
    private static Object[][] clientsAndMoney() {

        return new Object[][]{
                {"Hermoine Granger", "1001", "1000", "Dollar"},
                {"Hermoine Granger", "1002", "1", "Pound"},
                {"Hermoine Granger", "1003", "9999999999", "Rupee"}
        };
    }

}
