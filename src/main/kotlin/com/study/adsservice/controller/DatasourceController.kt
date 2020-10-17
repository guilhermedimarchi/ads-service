package com.study.adsservice.controller

import com.study.adsservice.model.Metric
import com.study.adsservice.service.DatasourceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/datasources/{id}")
class DatasourceController(private val datasourceService: DatasourceService) {


    @GetMapping("/metrics")
    fun getMetrics(@PathVariable id: String) : List<Metric> {
        return datasourceService.getMetrics(id)
    }


}
