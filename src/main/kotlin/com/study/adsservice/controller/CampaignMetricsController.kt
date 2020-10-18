package com.study.adsservice.controller

import com.study.adsservice.model.Metric
import com.study.adsservice.model.Summary
import com.study.adsservice.service.CampaignService
import com.study.adsservice.service.MetricService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/campaigns/{id}")
class CampaignMetricsController(private val campaignService: CampaignService,
                                private val metricService: MetricService) {

    @GetMapping("/metrics")
    fun getMetrics(@PathVariable id: String) : ResponseEntity<List<Metric>> {
        if(campaignService.findById(id).isEmpty)
            return ResponseEntity.notFound().build()

        return ResponseEntity.ok(metricService.getMetricsByCampaignId(id))
    }

    @GetMapping("/summary")
    fun getMetricsSummary(@PathVariable id: String) : ResponseEntity<Summary> {
        if(campaignService.findById(id).isEmpty)
            return ResponseEntity.notFound().build()

        return ResponseEntity.ok(metricService.getMetricsSummaryByCampaignId(id))
    }
}
