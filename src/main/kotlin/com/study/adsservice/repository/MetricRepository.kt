package com.study.adsservice.repository

import com.study.adsservice.domain.Metric
import org.springframework.data.jpa.repository.JpaRepository

interface MetricRepository : JpaRepository<Metric, Long> {

}
