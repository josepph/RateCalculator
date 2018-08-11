package com.jp.ratecalculator;

import com.jp.ratecalculator.domain.Lender;
import com.jp.ratecalculator.domain.Quote;
import com.jp.ratecalculator.service.*;

import java.util.*;
import java.util.stream.Collectors;

import static com.jp.ratecalculator.configuration.Constants.*;

public class LoanRateCalculator {

    public static void main(String[] args) {

        try {

            // validates parameters. If all fine, proceed. Raise exception otherwise.
            new ValidatorService().validInputParameters(args);

            String filePathName = args[0];
            Integer loanAmount = Integer.parseInt(args[1]);

            // load list of lenders and sort it by offered rate field
            DataLoaderService dataLoaderService = new DataLoaderService();
            List<Lender> sortedLendersList = dataLoaderService.loadMarketDataFile(filePathName);

            // proceed only if there is enough money available to cover requested loan
            Integer totalAvailable = sortedLendersList.stream().collect(Collectors.summingInt(Lender::getAvailableAmount));
            if (totalAvailable < loanAmount) {
                // not enough money available to cover the requested loan
                System.out.println(LOAN_UNAVAILABLE);
                System.exit(0);
            }

            // perform calculations and obtain final quote to offer to the borrower
            Quote obtainedQuote = new QuoteService().obtainQuote(
                                                        sortedLendersList,
                                                        loanAmount,
                                                        LOAN_NUMBER_OF_MONTHS);

            // write final data to output, console in this case
            OutputService outputGeneratorService = new OutputService();
            outputGeneratorService.ConsoleOutput(obtainedQuote);

        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            System.out.println("Error: input parameters are wrong. Please review those and run again.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
