package com.study.adsservice.controller

import com.study.adsservice.model.Metric
import com.study.adsservice.model.Summary
import com.study.adsservice.service.DatasourceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/datasources/{id}")
class DatasourceController(private val datasourceService: DatasourceService) {

    @GetMapping("/metrics")
    fun getMetrics(@PathVariable id: String) : ResponseEntity<List<Metric>> {
        val metrics = datasourceService.getMetrics(id)
        if (metrics.isPresent)
            return ResponseEntity.ok(metrics.get())
        return ResponseEntity.notFound().build()
    }

    @GetMapping("/metrics/summary")
    fun getMetricsSummary(@PathVariable id: String) : Summary {
        return datasourceService.getMetricsSummary(id).get()
    }
}
