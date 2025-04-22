package com.our_pharma_corp.pharma_sales_estimation_backend.controllers;

import com.our_pharma_corp.pharma_sales_estimation_backend.dtos.CompanySalesDto;
import com.our_pharma_corp.pharma_sales_estimation_backend.dtos.DrugEstimationDTO;
import com.our_pharma_corp.pharma_sales_estimation_backend.dtos.ResultSummaryDTO;
import com.our_pharma_corp.pharma_sales_estimation_backend.dtos.TopDrugDto;
import com.our_pharma_corp.pharma_sales_estimation_backend.services.ResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@CrossOrigin(origins = "*")
public class ResultsController {

    @Autowired
    private JdbcTemplate jdbc;

    private final ResultsService resultsService;

    public ResultsController(ResultsService resultsService) {
        this.resultsService = resultsService;
    }

    @GetMapping("/quarters")
    public List<String> getAvailableQuarters() {
        return jdbc.queryForList(
                "SELECT DISTINCT quarter FROM estimated_pharma_sales ORDER BY quarter DESC",
                String.class
        );
    }

    @GetMapping("/summary")
    public ResultSummaryDTO getSummary(@RequestParam String quarter) {
        String totalSalesQuery = "SELECT COALESCE(SUM(mean_sales), 0) FROM estimated_pharma_sales WHERE quarter = ?";
        String ourCompanySalesQuery = "SELECT COALESCE(SUM(mean_sales), 0) FROM estimated_pharma_sales WHERE quarter = ? AND company_id = (SELECT company_id FROM companies WHERE company_name = 'OurPharmaCorp' LIMIT 1)";
        String totalDrugsQuery = "SELECT COUNT(DISTINCT drug_id) FROM estimated_pharma_sales WHERE quarter = ?";
        String totalCountriesQuery = "SELECT COUNT(DISTINCT country_id) FROM estimated_pharma_sales WHERE quarter = ?";
        String totalCompaniesQuery = "SELECT COUNT(DISTINCT company_id) FROM estimated_pharma_sales WHERE quarter = ?";

        double totalSales = jdbc.queryForObject(totalSalesQuery, Double.class, quarter);
        double ourCompanySales = jdbc.queryForObject(ourCompanySalesQuery, Double.class, quarter);
        int totalDrugs = jdbc.queryForObject(totalDrugsQuery, Integer.class, quarter);
        int totalCountries = jdbc.queryForObject(totalCountriesQuery, Integer.class, quarter);
        int totalCompanies = jdbc.queryForObject(totalCompaniesQuery, Integer.class, quarter);

        return new ResultSummaryDTO(quarter, totalSales, totalCountries, totalDrugs, totalCompanies, ourCompanySales);
    }

    @GetMapping("/drugs")
    public List<DrugEstimationDTO> getDrugEstimations(
            @RequestParam String quarter,
            @RequestParam(required = false) Integer countryId,
            @RequestParam(required = false) Integer categoryId) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT d.drug_name, c.company_name, cat.category_name, co.country_name, ")
                .append("e.rank, e.mean_sales, e.min_sales, e.max_sales ")
                .append("FROM estimated_pharma_sales e ")
                .append("JOIN drugs d ON e.drug_id = d.drug_id ")
                .append("JOIN companies c ON e.company_id = c.company_id ")
                .append("JOIN categories cat ON e.category_id = cat.category_id ")
                .append("JOIN countries co ON e.country_id = co.country_id ")
                .append("WHERE e.quarter = ? ");

        // Add filters dynamically
        if (countryId != null) {
            sql.append("AND e.country_id = ").append(countryId).append(" ");
        }
        if (categoryId != null) {
            sql.append("AND e.category_id = ").append(categoryId).append(" ");
        }

        sql.append("ORDER BY e.rank ASC");

        return jdbc.query(
                sql.toString(),
                (rs, rowNum) -> new DrugEstimationDTO(
                        rs.getString("drug_name"),
                        rs.getString("company_name"),
                        rs.getString("category_name"),
                        rs.getString("country_name"),
                        rs.getInt("rank"),
                        rs.getDouble("mean_sales"),
                        rs.getDouble("min_sales"),
                        rs.getDouble("max_sales")
                ),
                quarter
        );
    }

    @GetMapping("/top-drugs")
    public List<TopDrugDto> getTopDrugs(@RequestParam String quarter) {
        return resultsService.getTopDrugs(quarter);
    }

    @GetMapping("/company-sales")
    public List<CompanySalesDto> getCompanySales(@RequestParam String quarter) {
        return resultsService.getCompanySales(quarter);
    }
}
