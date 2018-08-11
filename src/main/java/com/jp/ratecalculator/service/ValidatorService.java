package com.jp.ratecalculator.service;

import com.jp.ratecalculator.exception.IncorrectLoanAmount;

import static com.jp.ratecalculator.configuration.Constants.*;


public class ValidatorService {

    // true if mandatory arguments exist and contain valid values
    public Boolean validInputParameters(String[] args) throws Exception {

        // first command-line parameter is market data file
        String fileName = args[0];
        if (fileName.length() == 0)
            throw new Exception("Error: input file path cannot be null.");

        // second command-line parameter is requested loan amount
        Integer requestedAmount = Integer.parseInt(args[1]);
        if (requestedAmount < MINIMUM_LOAN_AMOUNT)
            throw new IncorrectLoanAmount(
                    "Error: requested amount of " + CURRENCY_SYMBOL + requestedAmount +
                                    " is below minimum, currently set at " +
                                    CURRENCY_SYMBOL + MINIMUM_LOAN_AMOUNT + ".");
        if (requestedAmount > MAXIMUM_LOAN_AMOUNT)
            throw new IncorrectLoanAmount(
                    "Error: requested amount of " + CURRENCY_SYMBOL + requestedAmount +
                                    " is above maximum, currently set at " +
                                    CURRENCY_SYMBOL + MAXIMUM_LOAN_AMOUNT + ".");
        if ((requestedAmount % ALLOWED_INCREMENT) != 0)
            throw new IncorrectLoanAmount(
                    "Error: requested amount has to be multiple of "
                            + ALLOWED_INCREMENT + " and " + CURRENCY_SYMBOL + requestedAmount + " is not.");

        // validation is successful
        return true;
    }


}
