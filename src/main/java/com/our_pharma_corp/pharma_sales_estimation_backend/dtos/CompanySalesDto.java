package com.our_pharma_corp.pharma_sales_estimation_backend.dtos;

public class CompanySalesDto {
    private String companyName;
    private double totalSales;

    public CompanySalesDto(String companyName, double totalSales) {
        this.companyName = companyName;
        this.totalSales = totalSales;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }
}

