package com.our_pharma_corp.pharma_sales_estimation_backend.dtos;

public class CompanyDrugDetailDTO {
    private String drugName;
    private String countryName;
    private String categoryName;
    private Integer rank;
    private Double estimatedSales;
    private Double minSales;
    private Double maxSales;

    public CompanyDrugDetailDTO() {
    }

    public CompanyDrugDetailDTO(String drugName, String countryName, String categoryName, Integer rank, Double estimatedSales, Double minSales, Double maxSales) {
        this.drugName = drugName;
        this.countryName = countryName;
        this.categoryName = categoryName;
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

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Double getEstimatedSales() {
        return estimatedSales;
    }

    public void setEstimatedSales(Double estimatedSales) {
        this.estimatedSales = estimatedSales;
    }

    public Double getMinSales() {
        return minSales;
    }

    public void setMinSales(Double minSales) {
        this.minSales = minSales;
    }

    public Double getMaxSales() {
        return maxSales;
    }

    public void setMaxSales(Double maxSales) {
        this.maxSales = maxSales;
    }
}

