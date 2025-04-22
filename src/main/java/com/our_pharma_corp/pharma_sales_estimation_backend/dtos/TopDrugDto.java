package com.our_pharma_corp.pharma_sales_estimation_backend.dtos;

public class TopDrugDto {
    private String drugName;
    private double estimatedSales;

    public TopDrugDto(String drugName, double estimatedSales) {
        this.drugName = drugName;
        this.estimatedSales = estimatedSales;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public double getEstimatedSales() {
        return estimatedSales;
    }

    public void setEstimatedSales(double estimatedSales) {
        this.estimatedSales = estimatedSales;
    }
}

