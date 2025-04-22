package com.our_pharma_corp.pharma_sales_estimation_backend.dtos;

import java.util.List;

public class CompanySummaryDTO {
    private Double totalSales;
    private List<SimpleSalesDTO> topCountries;
    private List<SimpleSalesDTO> topCategories;

    public CompanySummaryDTO() {
    }

    public CompanySummaryDTO(Double totalSales, List<SimpleSalesDTO> topCountries, List<SimpleSalesDTO> topCategories) {
        this.totalSales = totalSales;
        this.topCountries = topCountries;
        this.topCategories = topCategories;
    }

    public Double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Double totalSales) {
        this.totalSales = totalSales;
    }

    public List<SimpleSalesDTO> getTopCountries() {
        return topCountries;
    }

    public void setTopCountries(List<SimpleSalesDTO> topCountries) {
        this.topCountries = topCountries;
    }

    public List<SimpleSalesDTO> getTopCategories() {
        return topCategories;
    }

    public void setTopCategories(List<SimpleSalesDTO> topCategories) {
        this.topCategories = topCategories;
    }
}

