package com.study.adsservice.controller

import com.study.adsservice.model.Datasource
import com.study.adsservice.model.Metric
import com.study.adsservice.model.Summary
import com.study.adsservice.service.DatasourceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/datasources")
class DatasourceController(private val datasourceService: DatasourceService) {

    @GetMapping
    fun getDatasources() : ResponseEntity<List<Datasource>> {
        return ResponseEntity.ok(datasourceService.findAll())
    }

    @GetMapping("/{id}")
    fun getDatasourcesById(@PathVariable id: String) : ResponseEntity<Datasource> {
        val ds = datasourceService.findById(id)
        if (ds.isPresent)
            return ResponseEntity.ok(ds.get())
        return ResponseEntity.notFound().build()
    }

    @GetMapping("/{id}/metrics")
    fun getMetrics(@PathVariable id: String) : ResponseEntity<List<Metric>> {
        val metrics = datasourceService.getMetrics(id)
        if (metrics.isPresent)
            return ResponseEntity.ok(metrics.get())
        return ResponseEntity.notFound().build()
    }

    @GetMapping("/{id}/metrics/summary")
    fun getMetricsSummary(@PathVariable id: String) : ResponseEntity<Summary> {
        val summary = datasourceService.getMetricsSummary(id)
        if (summary.isPresent)
            return ResponseEntity.ok(summary.get())
        return ResponseEntity.notFound().build()
    }
}
