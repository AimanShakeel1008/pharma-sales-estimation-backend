package com.our_pharma_corp.pharma_sales_estimation_backend.repositories;

import com.our_pharma_corp.pharma_sales_estimation_backend.entities.EstimationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EstimationStatusRepository extends JpaRepository<EstimationStatus, Long> {

    @Query("SELECT e FROM EstimationStatus e WHERE e.quarter = :quarter ORDER BY e.startedAt DESC LIMIT 1")
    EstimationStatus findLatestByQuarter(String quarter);

    @Query("SELECT e FROM EstimationStatus e ORDER BY e.startedAt DESC")
    List<EstimationStatus> findAllByOrderByStartedAtDesc();
}

