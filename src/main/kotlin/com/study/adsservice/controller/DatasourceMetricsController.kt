package com.study.adsservice.controller

import com.study.adsservice.model.Metric
import com.study.adsservice.model.Summary
import com.study.adsservice.service.DatasourceMetricsService
import com.study.adsservice.service.DatasourceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/datasources/{id}")
class DatasourceMetricsController(private val datasourceService: DatasourceService,
                                  private val metricsService: DatasourceMetricsService) {

    @GetMapping("/metrics")
    fun getMetrics(@PathVariable id: String) : ResponseEntity<List<Metric>> {
        if(datasourceService.findById(id).isEmpty)
            return ResponseEntity.notFound().build()

        return ResponseEntity.ok(metricsService.getMetrics(id))
    }

    @GetMapping("/metrics/summary")
    fun getMetricsSummary(@PathVariable id: String) : ResponseEntity<Summary> {
        if(datasourceService.findById(id).isEmpty)
            return ResponseEntity.notFound().build()

        return ResponseEntity.ok(metricsService.getMetricsSummary(id))
    }
}
