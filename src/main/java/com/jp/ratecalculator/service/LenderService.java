package com.jp.ratecalculator.service;

import com.jp.ratecalculator.domain.Lender;

import java.util.ArrayList;
import java.util.List;

public class LenderService {

    // obtains the list of lenders required to cover the requested loan
    // and the amount required from each one
    public List<Lender> getListOfRequiredLenders(List<Lender> allLenders, Integer loanAmount) {

        List<Lender> requiredLenders = new ArrayList<>();
        Integer coveredAmount = 0;
        for(Lender lenderElement : allLenders) {

            coveredAmount += lenderElement.getAvailableAmount();

            if (coveredAmount <= loanAmount) {
                requiredLenders.add(lenderElement);
            } else {
                lenderElement.setAvailableAmount(lenderElement.getAvailableAmount() - (coveredAmount-loanAmount));
                requiredLenders.add(lenderElement);
                break;
            }

        }

        return requiredLenders;

    }

}
