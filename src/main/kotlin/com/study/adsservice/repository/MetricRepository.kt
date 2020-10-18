package com.study.adsservice.repository

import com.study.adsservice.domain.Metric
import com.study.adsservice.model.SummaryDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query

interface MetricRepository : JpaRepository<Metric, Long>, JpaSpecificationExecutor<Metric> {

}
