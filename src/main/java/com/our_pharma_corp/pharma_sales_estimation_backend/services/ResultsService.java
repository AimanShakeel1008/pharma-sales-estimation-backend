package com.our_pharma_corp.pharma_sales_estimation_backend.services;

import com.our_pharma_corp.pharma_sales_estimation_backend.dtos.CompanySalesDto;
import com.our_pharma_corp.pharma_sales_estimation_backend.dtos.TopDrugDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResultsService {

    private final JdbcTemplate jdbcTemplate;

    public ResultsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TopDrugDto> getTopDrugs(String quarter) {
        String sql = """
            SELECT drug_name AS drugName, SUM(mean_sales) AS estimatedSales
            FROM estimated_pharma_sales
            WHERE quarter = ?
            GROUP BY drug_name
            ORDER BY estimatedSales DESC
            LIMIT 10
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> new TopDrugDto(
                rs.getString("drugName"),
                rs.getDouble("estimatedSales")
        ), quarter);
    }

    public List<CompanySalesDto> getCompanySales(String quarter) {
        String sql = """
            SELECT company_name AS companyName, SUM(mean_sales) AS totalSales
            FROM estimated_pharma_sales
            WHERE quarter = ?
            GROUP BY company_name
            ORDER BY totalSales DESC
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> new CompanySalesDto(
                rs.getString("companyName"),
                rs.getDouble("totalSales")
        ), quarter);
    }
}

