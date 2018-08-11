package com.jp.ratecalculator.service;

import com.jp.ratecalculator.domain.Quote;

import java.text.DecimalFormat;

import static com.jp.ratecalculator.configuration.Constants.*;

public class OutputService {

    // output values on console
    public void ConsoleOutput (Quote obtainedQuote) {

        DecimalFormat repaymentsFormat = new DecimalFormat(REPAYMENT_FORMAT);
        DecimalFormat rateFormat = new DecimalFormat(RATE_FORMAT);

        System.out.println("Requested amount: " + CURRENCY_SYMBOL + obtainedQuote.getRequestedAmount());
        System.out.println("Rate: " + rateFormat.format(obtainedQuote.getResultingRate()));
        System.out.println("Monthly repayment: " + CURRENCY_SYMBOL + repaymentsFormat.format(obtainedQuote.getMonthlyRepayment()));
        System.out.println("Total repayment: " + CURRENCY_SYMBOL + repaymentsFormat.format(obtainedQuote.getTotalRepayment()));

    }

}
