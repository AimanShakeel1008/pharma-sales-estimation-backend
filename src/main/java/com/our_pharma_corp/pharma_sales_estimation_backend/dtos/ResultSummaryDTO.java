package com.our_pharma_corp.pharma_sales_estimation_backend.dtos;

import lombok.Data;

public class ResultSummaryDTO {
    private String quarter;
    private double totalSales;
    private int totalCountries;
    private int totalDrugs;
    private int totalCompanies;
    private double ourCompanySales;

    public ResultSummaryDTO(String quarter, double totalSales, int totalCountries, int totalDrugs, int totalCompanies, double ourCompanySales) {
        this.quarter = quarter;
        this.totalSales = totalSales;
        this.totalCountries = totalCountries;
        this.totalDrugs = totalDrugs;
        this.totalCompanies = totalCompanies;
        this.ourCompanySales = ourCompanySales;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public int getTotalCountries() {
        return totalCountries;
    }

    public void setTotalCountries(int totalCountries) {
        this.totalCountries = totalCountries;
    }

    public int getTotalDrugs() {
        return totalDrugs;
    }

    public void setTotalDrugs(int totalDrugs) {
        this.totalDrugs = totalDrugs;
    }

    public int getTotalCompanies() {
        return totalCompanies;
    }

    public void setTotalCompanies(int totalCompanies) {
        this.totalCompanies = totalCompanies;
    }

    public double getOurCompanySales() {
        return ourCompanySales;
    }

    public void setOurCompanySales(double ourCompanySales) {
        this.ourCompanySales = ourCompanySales;
    }
}

