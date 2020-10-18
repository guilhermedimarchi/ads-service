package com.study.adsservice.controller

import com.study.adsservice.model.MetricDTO
import com.study.adsservice.model.SummaryDTO
import com.study.adsservice.service.DatasourceService
import com.study.adsservice.service.MetricService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/metrics")
class MetricsController(private val metricService: MetricService) {

    @GetMapping
    fun getMetrics(@RequestParam params: MutableMap<String,String>) : ResponseEntity<List<MetricDTO>> {
        return ResponseEntity.ok(metricService.findAllBy(params))
    }

    @GetMapping("/summary")
    fun getMetricsSummary(@RequestParam params: MutableMap<String,String>) : ResponseEntity<SummaryDTO> {
        return ResponseEntity.ok(metricService.getSummaryBy(params))
    }
}
