package com.our_pharma_corp.pharma_sales_estimation_backend.dtos;

import java.util.List;


public class CountrySummaryDTO {
    private double totalSales;
    private List<SimpleSalesDTO> topCategories;
    private List<SimpleSalesDTO> topCompanies;

    public CountrySummaryDTO() {
    }

    public CountrySummaryDTO(double totalSales, List<SimpleSalesDTO> topCategories, List<SimpleSalesDTO> topCompanies) {
        this.totalSales = totalSales;
        this.topCategories = topCategories;
        this.topCompanies = topCompanies;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public List<SimpleSalesDTO> getTopCategories() {
        return topCategories;
    }

    public void setTopCategories(List<SimpleSalesDTO> topCategories) {
        this.topCategories = topCategories;
    }

    public List<SimpleSalesDTO> getTopCompanies() {
        return topCompanies;
    }

    public void setTopCompanies(List<SimpleSalesDTO> topCompanies) {
        this.topCompanies = topCompanies;
    }
}


