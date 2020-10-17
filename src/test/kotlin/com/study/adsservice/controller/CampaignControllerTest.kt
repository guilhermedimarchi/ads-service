package com.study.adsservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.adsservice.model.Campaign
import com.study.adsservice.service.CampaignService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*


@WebMvcTest(CampaignController::class)
class CampaignControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @MockBean
    private lateinit var campaignService: CampaignService

    private val campaign = Campaign("1", "Retargeting")

    @Test
    fun `should return list of campaign`() {
        val campaigns = listOf(campaign)
        given(campaignService.findAll()).willReturn(campaigns)

        mockMvc.perform(MockMvcRequestBuilders.get("/campaigns"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(campaigns)))

        verify(campaignService, times(1)).findAll()
    }

    @Test
    fun `should return campaign if id exists`() {
        val id = "1"
        given(campaignService.findById(id)).willReturn(Optional.of(campaign))

        mockMvc.perform(MockMvcRequestBuilders.get("/campaigns/$id"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(campaign)))

        verify(campaignService, times(1)).findById(id)
    }

    @Test
    fun `should return not found if campaign id does not exists`() {
        val id = "unknown"
        given(campaignService.findById(id)).willReturn(Optional.empty())

        mockMvc.perform(MockMvcRequestBuilders.get("/campaigns/$id"))
                .andExpect(MockMvcResultMatchers.status().isNotFound)

        verify(campaignService, times(1)).findById(id)
    }

}
