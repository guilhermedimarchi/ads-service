package com.study.adsservice.service

import com.study.adsservice.domain.Campaign
import com.study.adsservice.repository.CampaignRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class CampaignServiceTest {

    @Mock
    private lateinit var repository: CampaignRepository

    private lateinit var service : CampaignService

    @BeforeEach
    fun setup() {
        service = CampaignService(repository)
    }

    @Test
    fun `should find all campaings`() {
        given(repository.findAll()).willReturn(listOf(Campaign(1L, "Dummy")))

        val campaigns = service.findAll()

        assertThat(campaigns.size).isEqualTo(1)
        assertThat(campaigns.first().id).isEqualTo("1")
        assertThat(campaigns.first().name).isEqualTo("Dummy")
        verify(repository, times(1)).findAll()
    }

    @Test
    fun `should return campaing if id exists`() {
        given(repository.findById(1L)).willReturn(Optional.of(Campaign(1L, "Dummy")))

        val campaign = service.findById(1L).get()

        assertThat(campaign.id).isEqualTo("1")
        assertThat(campaign.name).isEqualTo("Dummy")
        verify(repository, times(1)).findById(1L)
    }

    @Test
    fun `should return empty if id does not exists`() {
        given(repository.findById(1L)).willReturn(Optional.empty())

        val campaign = service.findById(1L)

        assertThat(campaign.isPresent).isFalse()
        verify(repository, times(1)).findById(1L)
    }


}
