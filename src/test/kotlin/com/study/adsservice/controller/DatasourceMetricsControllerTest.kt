package com.study.adsservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.adsservice.model.Metric
import com.study.adsservice.model.Summary
import com.study.adsservice.service.DatasourceService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.Instant
import java.util.*

@WebMvcTest(DatasourceMetricsController::class)
class DatasourceMetricsControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper : ObjectMapper

    @MockBean
    private lateinit var datasourceService: DatasourceService

    private val id = "1"

    private val unknownId = "unknown"


    @Test
    fun `should return metrics for a given datasource`() {
        val metrics = listOf(Metric(Instant.ofEpochSecond(1L), 3000, 100))
        given(datasourceService.getMetrics(id)).willReturn(Optional.of(metrics))

        mockMvc.perform(get("/datasources/$id/metrics"))
                .andExpect(content().string(mapper.writeValueAsString(metrics)))
                .andExpect(status().isOk)

        Mockito.verify(datasourceService, times(1)).getMetrics(id)
    }

    @Test
    fun `should return not found for unknown datasource`() {
        given(datasourceService.getMetrics(unknownId)).willReturn(Optional.empty())

        mockMvc.perform(get("/datasources/$unknownId/metrics"))
                .andExpect(status().isNotFound)

        Mockito.verify(datasourceService, times(1)).getMetrics(unknownId)
    }

    @Test
    fun `should return metrics summary for a given datasource`() {
        val summary = Summary(100,10,10)
        given(datasourceService.getMetricsSummary(id)).willReturn(Optional.of(summary))

        mockMvc.perform(get("/datasources/$id/metrics/summary"))
                .andExpect(content().string(mapper.writeValueAsString(summary)))
                .andExpect(status().isOk)

        Mockito.verify(datasourceService, times(1)).getMetricsSummary(id)
    }

    @Test
    fun `should return not found for unknown datasource summary`() {
        given(datasourceService.getMetricsSummary(unknownId)).willReturn(Optional.empty())

        mockMvc.perform(get("/datasources/$unknownId/metrics/summary"))
                .andExpect(status().isNotFound)

        Mockito.verify(datasourceService, times(1)).getMetricsSummary(unknownId)
    }

}

