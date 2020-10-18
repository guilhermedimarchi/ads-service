package com.study.adsservice.repository

import com.study.adsservice.domain.Metric
import com.study.adsservice.model.SummaryDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query

interface MetricRepository : JpaRepository<Metric, Long>, JpaSpecificationExecutor<Metric> {

    @Query("SELECT m FROM Metric m WHERE m.datasource.id = :datasourceId ORDER BY m.campaign, m.daily")
    fun findByDatasourceId(datasourceId: Long): List<Metric>

    @Query("""SELECT new com.study.adsservice.model.SummaryDTO(sum(m.clicks), sum(m.impressions)) 
              FROM Metric m 
              WHERE m.datasource.id = :datasourceId
              GROUP BY :datasourceId""")
    fun getMetricsSummaryByDatasourceId(datasourceId: Long) : SummaryDTO


}
