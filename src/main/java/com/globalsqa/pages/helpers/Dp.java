package com.globalsqa.pages.helpers;

import org.testng.annotations.DataProvider;

public class Dp {

    @DataProvider(name = "customerMoneyDataPositive")
    private static Object[][] customersAndMoney() {

        return new Object[][]{
                {"Hermoine Granger", "1001", "1000", "Dollar"},
                {"Hermoine Granger", "1002", "1", "Pound"},
                {"Hermoine Granger", "1003", "9999999999", "Rupee"}
        };
    }

    @DataProvider(name = "customerMoneyDataNegative")
    private static Object[][] customersAndMoneyNegative() {
        return new Object[][]{
                {"Hermoine Granger", "1001", "6000"},
                {"Hermoine Granger", "1001", "-1"},
                {"Hermoine Granger", "1002", "0"},
                {"Hermoine Granger", "1002", ""},
                {"Hermoine Granger", "1003", "%$^"},
                {"Hermoine Granger", "1003", "asd"}
        };

    }


}
