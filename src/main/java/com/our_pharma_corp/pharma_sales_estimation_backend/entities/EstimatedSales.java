package com.our_pharma_corp.pharma_sales_estimation_backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "estimated_pharma_sales")
public class EstimatedSales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "quarter")
    private String quarter;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "drug_id")
    private Integer drugId;

    @Column(name = "drug_name")
    private String drugName;

    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "mean_sales")
    private Double meanSales;

    @Column(name = "min_sales")
    private Double minSales;

    @Column(name = "max_sales")
    private Double maxSales;

    // 🔥 Default constructor
    public EstimatedSales() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getDrugId() {
        return drugId;
    }

    public void setDrugId(Integer drugId) {
        this.drugId = drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Double getMeanSales() {
        return meanSales;
    }

    public void setMeanSales(Double meanSales) {
        this.meanSales = meanSales;
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
