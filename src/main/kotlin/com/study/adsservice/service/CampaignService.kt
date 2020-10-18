package com.study.adsservice.service

import com.study.adsservice.model.CampaignDTO
import com.study.adsservice.repository.CampaignRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CampaignService(private val repository: CampaignRepository) {

    fun findAll(): List<CampaignDTO> {
        val campaigns = repository.findAll()
        return CampaignDTO.fromList(campaigns)
    }

    fun findById(id: Long): Optional<CampaignDTO> {
        val campaign = repository.findById(id)
        if(campaign.isEmpty)
            return Optional.empty()
        return Optional.of(CampaignDTO.fromCampaign(campaign.get()))
    }
}
