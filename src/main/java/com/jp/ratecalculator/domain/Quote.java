package com.jp.ratecalculator.domain;

public class Quote {

    private Integer requestedAmount;
    private Double resultingRate;
    private Double monthlyRepayment;
    private Double totalRepayment;

    public Quote(Integer requestedAmount,
                 Double resultingRate,
                 Double monthlyRepayment,
                 Double totalRepayment) {

        this.requestedAmount = requestedAmount;
        this.resultingRate = resultingRate;
        this.monthlyRepayment = monthlyRepayment;
        this.totalRepayment = totalRepayment;

    }

    public Integer getRequestedAmount() {
        return requestedAmount;
    }

    public Double getResultingRate() {
        return resultingRate;
    }

    public Double getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public Double getTotalRepayment() { return totalRepayment; }

}
