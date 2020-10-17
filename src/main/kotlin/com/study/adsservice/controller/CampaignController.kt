package com.study.adsservice.controller

import com.study.adsservice.model.Campaign
import com.study.adsservice.service.CampaignService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/campaigns")
class CampaignController (private val campaignService: CampaignService){

    @GetMapping
    fun getCampaigns() : ResponseEntity<List<Campaign>> {
        return ResponseEntity.ok(campaignService.findAll())
    }

    @GetMapping("/{id}")
    fun getCampaignsById(@PathVariable id: String) : ResponseEntity<Campaign> {
        val c = campaignService.findById(id)
        if (c.isPresent)
            return ResponseEntity.ok(c.get())
        return ResponseEntity.notFound().build()
    }

}
