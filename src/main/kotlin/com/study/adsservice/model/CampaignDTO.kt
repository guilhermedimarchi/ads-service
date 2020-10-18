package com.study.adsservice.model

import com.study.adsservice.domain.Campaign

data class CampaignDTO(
        val id: String,
        val name: String
) {
    companion object {
        fun fromCampaign(campaign : Campaign) : CampaignDTO {
            return CampaignDTO(
                    id = campaign.id.toString(),
                    name = campaign.name
            )
        }

        fun fromList(campaigns: List<Campaign>) : List<CampaignDTO> {
            val list = mutableListOf<CampaignDTO>()
            campaigns.forEach {
                list.add(fromCampaign(it))
            }
            return list
        }
    }
}
