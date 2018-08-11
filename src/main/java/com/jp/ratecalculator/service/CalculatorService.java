package com.jp.ratecalculator.service;

import com.jp.ratecalculator.domain.Lender;

import java.util.ArrayList;
import java.util.List;

import static com.jp.ratecalculator.configuration.Constants.PERIODS_IN_YEAR;

public class CalculatorService {

    // the rate offered by each lender is weighted against the amount required to cover the loan,
    // so the final rate is directly impacted by this operation
    public double calculateResultingRate(List<Lender> usedLenders, Integer totalLoanAmount) {

        List<Double> lenderWeight = new ArrayList<>();
        List<Double> lenderWeightedRate = new ArrayList<>();

        for(Lender lenderElement : usedLenders) {

            double currentAvailableAmountWeight;

            currentAvailableAmountWeight =  ((double)lenderElement.getAvailableAmount() / ((double)totalLoanAmount));
            lenderWeight.add(currentAvailableAmountWeight);

            lenderWeightedRate.add(lenderElement.getOfferedRate() * currentAvailableAmountWeight);

        }

        return lenderWeightedRate.stream().reduce(0.00, Double::sum);

    }


    public double calculateTotalRepayment(Integer totalLoanAmount, double rate, Integer numberOfMonths) {

        // formula considers years, that's why transforming months into years
        return totalLoanAmount * Math.pow( ( 1 + rate ), ( numberOfMonths / PERIODS_IN_YEAR ) );

    }


    public double calculateMonthlyRepayment(double totalRepayment, Integer numberOfMonths) {

        return totalRepayment / (double) numberOfMonths;

    }

}
