package com.jp.ratecalculator.service;


import com.jp.ratecalculator.domain.Lender;
import com.jp.ratecalculator.domain.Quote;

import java.text.DecimalFormat;
import java.util.List;

public class QuoteService {

    // obtains the target quote, considering all specified factors
    public Quote obtainQuote(List<Lender> lendersList,
                             Integer loanAmount,
                             Integer numberOfMonths) {

        // get the list of lenders that are going to be required to cover the loan
        // note that the lenders' list is already sorted by offered rate
        LenderService lenderService = new LenderService();
        List<Lender> requiredLenders = lenderService.getListOfRequiredLenders(lendersList, loanAmount);

        // we now need to get the overall loan rate
        // to do so, we calculate the weighted percentage of each:
        CalculatorService calculator = new CalculatorService();
        Double finalRate = calculator.calculateResultingRate(requiredLenders, loanAmount);

        // as per requirements, final rate needs to be limited to 1 decimal here,
        // otherwise we are calculating using a value different than what we notify to borrower
        DecimalFormat rateValueFormat = new DecimalFormat("##.###");
        String stringFinalRate = rateValueFormat.format(finalRate).replace(",", ".");
        finalRate = Double.parseDouble(stringFinalRate);

        Double totalRepayment = calculator.calculateTotalRepayment(loanAmount, finalRate, numberOfMonths);
        Double monthlyRepayment = calculator.calculateMonthlyRepayment(totalRepayment, numberOfMonths);

        return (
                new Quote (
                        loanAmount,
                        finalRate,
                        monthlyRepayment,
                        totalRepayment
                    )
                );

    }


}
