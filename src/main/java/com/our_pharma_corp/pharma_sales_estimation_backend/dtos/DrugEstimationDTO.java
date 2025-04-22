package com.our_pharma_corp.pharma_sales_estimation_backend.dtos;

public class DrugEstimationDTO {
    private String drugName;
    private String companyName;
    private String categoryName;
    private String countryName;
    private int rank;
    private double estimatedSales;
    private double minSales;
    private double maxSales;

    public DrugEstimationDTO(String drugName, String companyName, String categoryName,
                             String countryName, int rank, double estimatedSales,
                             double minSales, double maxSales) {
        this.drugName = drugName;
        this.companyName = companyName;
        this.categoryName = categoryName;
        this.countryName = countryName;
        this.rank = rank;
        this.estimatedSales = estimatedSales;
        this.minSales = minSales;
        this.maxSales = maxSales;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getEstimatedSales() {
        return estimatedSales;
    }

    public void setEstimatedSales(double estimatedSales) {
        this.estimatedSales = estimatedSales;
    }

    public double getMinSales() {
        return minSales;
    }

    public void setMinSales(double minSales) {
        this.minSales = minSales;
    }

    public double getMaxSales() {
        return maxSales;
    }

    public void setMaxSales(double maxSales) {
        this.maxSales = maxSales;
    }
}
