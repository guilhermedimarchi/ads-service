package com.study.adsservice.controller

import com.study.adsservice.model.MetricDTO
import com.study.adsservice.model.SummaryDTO
import com.study.adsservice.service.DatasourceService
import com.study.adsservice.service.MetricService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/datasources/{id}")
class DatasourceMetricsController(private val datasourceService: DatasourceService,
                                  private val metricService: MetricService) {

    @GetMapping("/metrics")
    fun getMetrics(@PathVariable id: Long, @RequestParam params: MutableMap<String,String>) : ResponseEntity<List<MetricDTO>> {
        if(datasourceService.findById(id).isEmpty)
            return ResponseEntity.notFound().build()

        params["datasourceId"] = id.toString()
        return ResponseEntity.ok(metricService.findAllBy(params))
    }

    @GetMapping("/summary")
    fun getMetricsSummary(@PathVariable id: Long, @RequestParam params: MutableMap<String,String>) : ResponseEntity<SummaryDTO> {
        if(datasourceService.findById(id).isEmpty)
            return ResponseEntity.notFound().build()

        params["datasourceId"] = id.toString()
        return ResponseEntity.ok(metricService.getSummaryBy(params))
    }
}
