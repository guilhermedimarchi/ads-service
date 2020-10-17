package com.study.adsservice.controller

import com.study.adsservice.model.Campaign
import com.study.adsservice.model.Datasource
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

    @GetMapping("/{id}/campaigns")
    fun getCampaigns(@PathVariable id: String) : ResponseEntity<List<Campaign>> {
        val ds = datasourceService.findById(id)
        if (ds.isPresent)
            return ResponseEntity.ok(ds.get().campaigns)
        return ResponseEntity.notFound().build()
    }
}
