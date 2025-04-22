package com.our_pharma_corp.pharma_sales_estimation_backend.dtos;

public class SimpleSalesDTO {
    private String name;
    private Double sales;

    public SimpleSalesDTO() {
    }

    public SimpleSalesDTO(String name, Double sales) {
        this.name = name;
        this.sales = sales;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSales() {
        return sales;
    }

    public void setSales(Double sales) {
        this.sales = sales;
    }
}

