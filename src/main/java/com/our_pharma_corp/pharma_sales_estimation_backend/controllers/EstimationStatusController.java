package com.our_pharma_corp.pharma_sales_estimation_backend.controllers;

import com.our_pharma_corp.pharma_sales_estimation_backend.entities.EstimationStatus;
import com.our_pharma_corp.pharma_sales_estimation_backend.services.EstimationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/estimation/status")
@CrossOrigin(origins = "*") // Optional: allow frontend access
public class EstimationStatusController {

    @Autowired
    private EstimationStatusService statusService;

    @GetMapping
    public Map<String, String> getLatestStatus(@RequestParam(required = false) String quarter) {
        EstimationStatus status = statusService.getLatestStatus(
                quarter != null ? quarter : getCurrentQuarter()
        );

        if (status == null) {
            return Map.of("status", "unknown");
        }

        return Map.of(
                "status", status.getStatus(),
                "quarter", status.getQuarter(),
                "message", status.getMessage() != null ? status.getMessage() : ""
        );
    }

    @GetMapping("/history")
    public List<EstimationStatus> getStatusHistory() {
        return statusService.getAllHistory();
    }

    private String getCurrentQuarter() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int quarter = (month - 1) / 3 + 1;
        int year = cal.get(Calendar.YEAR);
        return year + "Q" + quarter;
    }
}

