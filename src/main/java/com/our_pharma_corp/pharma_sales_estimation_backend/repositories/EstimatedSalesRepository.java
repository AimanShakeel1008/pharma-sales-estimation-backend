package com.our_pharma_corp.pharma_sales_estimation_backend.repositories;

import com.our_pharma_corp.pharma_sales_estimation_backend.entities.EstimatedSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstimatedSalesRepository extends JpaRepository<EstimatedSales, Long> {

    List<EstimatedSales> findByQuarter(String quarter);

    List<EstimatedSales> findByQuarterAndCompanyName(String quarter, String companyName);

    List<EstimatedSales> findByQuarterAndCountryName(String quarter, String countryName);

    List<EstimatedSales> findByCountryNameAndQuarter(String countryName, String quarter);
}

