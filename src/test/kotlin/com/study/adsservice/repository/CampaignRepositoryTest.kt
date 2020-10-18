package com.study.adsservice.repository

import com.study.adsservice.domain.Campaign
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class CampaignRepositoryTest {

    @Autowired
    private lateinit var repo : CampaignRepository

    @Test
    fun `should create campaign entity`() {
        val c = Campaign(1L, "Test")
        assertThat(c).isNotNull
        assertThat(c.id).isEqualTo(1L)
        assertThat(c.name).isEqualTo("Test")
    }

    @Test
    fun `should return list of campaign entites`() {
        val campaigns = repo.findAll()
        assertThat(campaigns.size).isEqualTo(2)
        assertThat(campaigns.first().id).isEqualTo(1L)
        assertThat(campaigns.first().name).isEqualTo("Retargeting")
    }

    @Test
    fun `should contain list of datasources inside a campaign`() {
        val campaign = repo.findAll().first()
        assertThat(campaign.datasources.size).isEqualTo(1)
    }
}
