package com.our_pharma_corp.pharma_sales_estimation_backend.services;

import com.our_pharma_corp.pharma_sales_estimation_backend.dtos.*;
import com.our_pharma_corp.pharma_sales_estimation_backend.entities.EstimatedSales;
import com.our_pharma_corp.pharma_sales_estimation_backend.repositories.EstimatedSalesRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultsService {

    private final JdbcTemplate jdbcTemplate;

    private final EstimatedSalesRepository estimatedSalesRepository;

    public ResultsService(JdbcTemplate jdbcTemplate, EstimatedSalesRepository estimatedSalesRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.estimatedSalesRepository = estimatedSalesRepository;
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

    public CompanySummaryDTO getCompanySummary(String companyName, String quarter) {
        String totalSalesSql = "SELECT SUM(mean_sales) FROM estimated_pharma_sales WHERE company_name = ? AND quarter = ?";
        Double totalSales = jdbcTemplate.queryForObject(totalSalesSql, Double.class, companyName, quarter);

        String topCountriesSql = "SELECT country_name, SUM(mean_sales) AS total_sales FROM estimated_pharma_sales WHERE company_name = ? AND quarter = ? GROUP BY country_name ORDER BY total_sales DESC LIMIT 5";
        List<SimpleSalesDTO> topCountries = jdbcTemplate.query(topCountriesSql,
                (rs, rowNum) -> new SimpleSalesDTO(rs.getString("country_name"), rs.getDouble("total_sales")),
                companyName, quarter);

        String topCategoriesSql = "SELECT category_name, SUM(mean_sales) AS total_sales FROM estimated_pharma_sales WHERE company_name = ? AND quarter = ? GROUP BY category_name ORDER BY total_sales DESC LIMIT 5";
        List<SimpleSalesDTO> topCategories = jdbcTemplate.query(topCategoriesSql,
                (rs, rowNum) -> new SimpleSalesDTO(rs.getString("category_name"), rs.getDouble("total_sales")),
                companyName, quarter);

        return new CompanySummaryDTO(totalSales != null ? totalSales : 0.0, topCountries, topCategories);
    }

    public List<CompanyDrugDetailDTO> getCompanyDetails(String companyName, String quarter) {
        String sql = "SELECT drug_name, country_name, category_name, rank, mean_sales, min_sales, max_sales " +
                "FROM estimated_pharma_sales WHERE company_name = ? AND quarter = ?";

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new CompanyDrugDetailDTO(
                        rs.getString("drug_name"),
                        rs.getString("country_name"),
                        rs.getString("category_name"),
                        rs.getInt("rank"),
                        rs.getDouble("mean_sales"),
                        rs.getDouble("min_sales"),
                        rs.getDouble("max_sales")
                ),
                companyName, quarter);
    }

    public CountrySummaryDTO getCountrySummary(String countryName, String quarter) {
        List<EstimatedSales> sales = estimatedSalesRepository
                .findByCountryNameAndQuarter(countryName, quarter);

        if (sales.isEmpty()) {
            return new CountrySummaryDTO(0, List.of(), List.of());
        }

        // Total Sales
        double totalSales = sales.stream().mapToDouble(EstimatedSales::getMeanSales).sum();

        // Top Categories
        List<SimpleSalesDTO> topCategories = sales.stream()
                .collect(Collectors.groupingBy(EstimatedSales::getCategoryName, Collectors.summingDouble(EstimatedSales::getMeanSales)))
                .entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .limit(5)
                .map(e -> new SimpleSalesDTO(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        // Top Companies
        List<SimpleSalesDTO> topCompanies = sales.stream()
                .collect(Collectors.groupingBy(EstimatedSales::getCompanyName, Collectors.summingDouble(EstimatedSales::getMeanSales)))
                .entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .limit(5)
                .map(e -> new SimpleSalesDTO(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        return new CountrySummaryDTO(totalSales, topCategories, topCompanies);
    }

}

