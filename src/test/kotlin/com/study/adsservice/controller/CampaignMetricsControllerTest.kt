package com.study.adsservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.adsservice.model.Campaign
import com.study.adsservice.model.Metric
import com.study.adsservice.model.Summary
import com.study.adsservice.service.CampaignService
import com.study.adsservice.service.MetricsService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.Instant
import java.util.*

@WebMvcTest(CampaignMetricsController::class)
class CampaignMetricsControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper : ObjectMapper

    @MockBean
    private lateinit var campaignService: CampaignService

    @MockBean
    private lateinit var metricsService: MetricsService

    @Nested
    inner class GivenExistingCampaign {
        private val id = "1"
        private val dummy = Campaign("","")
        private val metrics = listOf(Metric(Instant.ofEpochSecond(1L), 3000, 100))

        @BeforeEach
        fun setup() {
            given(campaignService.findById(id)).willReturn(Optional.of(dummy))
        }

        @Test
        fun `should return metrics for a given datasource`() {
            given(metricsService.getMetricsByCampaignId(id)).willReturn(metrics)
            mockMvc.perform(get("/campaigns/$id/metrics"))
                    .andExpect(content().string(mapper.writeValueAsString(metrics)))
                    .andExpect(status().isOk)

            verify(metricsService, times(1)).getMetricsByCampaignId(id)
        }

        @Test
        fun `should return metrics summary for a given datasource`() {
            val summary = Summary(100,10,10)
            given(metricsService.getMetricsSummaryByCampaignId(id)).willReturn(summary)

            mockMvc.perform(get("/campaigns/$id/summary"))
                    .andExpect(content().string(mapper.writeValueAsString(summary)))
                    .andExpect(status().isOk)

            verify(metricsService, times(1)).getMetricsSummaryByCampaignId(id)
        }
    }

    @Nested
    inner class GivenNotFoundCampaign {

        private val unknownId = "unknown"

        @BeforeEach
        fun setup() {
            given(campaignService.findById(unknownId)).willReturn(Optional.empty())
        }

        @Test
        fun `should return not found for unknown campaign`() {
            mockMvc.perform(get("/campaigns/$unknownId/metrics"))
                    .andExpect(status().isNotFound)

            verify(metricsService, times(0)).getMetricsByCampaignId(unknownId)
        }

        @Test
        fun `should return not found for unknown campaign summary`() {
            mockMvc.perform(get("/campaigns/$unknownId/summary"))
                    .andExpect(status().isNotFound)

            verify(metricsService, times(0)).getMetricsSummaryByCampaignId(unknownId)
        }
    }

}

