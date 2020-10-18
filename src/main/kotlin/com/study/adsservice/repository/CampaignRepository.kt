package com.study.adsservice.repository

import com.study.adsservice.domain.Campaign
import org.springframework.data.jpa.repository.JpaRepository

interface CampaignRepository : JpaRepository<Campaign, Long> {

}
