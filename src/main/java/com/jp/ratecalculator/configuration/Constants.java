package com.jp.ratecalculator.configuration;

import java.util.Currency;
import java.util.Locale;

public class Constants {

    // text to show if the available amount is not enough to cover requested amount
    // reasons for unavailability could or could not be told to the customer
    public final static String LOAN_UNAVAILABLE = "Loan unavailable at this moment.";

    // default values set by business
    public final static Integer LOAN_NUMBER_OF_MONTHS = 36;
    public final static Integer MINIMUM_LOAN_AMOUNT = 1000;
    public final static Integer MAXIMUM_LOAN_AMOUNT = 15000;
    public final static Integer ALLOWED_INCREMENT = 100;

    // default currency symbol in this case
    public final static String CURRENCY_SYMBOL = "£";

    // in this case, period is equal to month
    public final static Integer PERIODS_IN_YEAR = 12;

    // Decimal formats to apply as output
    public final static String REPAYMENT_FORMAT = "#.##";
    public final static String RATE_FORMAT = "##.##%";

}
