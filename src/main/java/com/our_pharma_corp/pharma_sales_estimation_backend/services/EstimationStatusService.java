package com.our_pharma_corp.pharma_sales_estimation_backend.services;

import com.our_pharma_corp.pharma_sales_estimation_backend.entities.EstimationStatus;
import com.our_pharma_corp.pharma_sales_estimation_backend.repositories.EstimationStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstimationStatusService {

    @Autowired
    private EstimationStatusRepository repository;

    public EstimationStatus getLatestStatus(String quarter) {
        return repository.findLatestByQuarter(quarter);
    }

    public List<EstimationStatus> getAllHistory() {
        return repository.findAllByOrderByStartedAtDesc();
    }
}

