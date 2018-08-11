package com.jp.ratecalculator.domain;

public class Lender {

    private String lenderName;
    private Double offeredRate;
    private Integer availableAmount;

    public Lender(String lenderName, Double offeredRate, Integer availableAmount) {
        this.lenderName = lenderName;
        this.offeredRate = offeredRate;
        this.availableAmount = availableAmount;
    }

    public Double getOfferedRate() {
        return offeredRate;
    }

    public Integer getAvailableAmount() { return availableAmount; }

    public void setAvailableAmount(Integer availableAmount) {
        this.availableAmount = availableAmount;
    }

}
