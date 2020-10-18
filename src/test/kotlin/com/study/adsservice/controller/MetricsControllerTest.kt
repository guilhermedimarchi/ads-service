package com.study.adsservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.adsservice.model.DatasourceDTO
import com.study.adsservice.model.MetricDTO
import com.study.adsservice.model.SummaryDTO
import com.study.adsservice.service.DatasourceService
import com.study.adsservice.service.MetricService
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
import java.time.LocalDate
import java.util.*

@WebMvcTest(MetricsController::class)
class MetricsControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper : ObjectMapper

    @MockBean
    private lateinit var metricService: MetricService

    private val metrics = listOf(MetricDTO("Facebook", "Sales", LocalDate.now(), 3000, 100))
    private val params = mapOf("datasourceId" to "1", "from" to "2019-01-27", "to" to "2019-03-15", "campaign" to "1")
    private val summary = SummaryDTO(100,10)

    @Test
    fun `should return metrics for a given datasource`() {
        given(metricService.findAllBy(emptyMap())).willReturn(metrics)
        mockMvc.perform(get("/metrics"))
                .andExpect(content().string(mapper.writeValueAsString(metrics)))
                .andExpect(status().isOk)

        verify(metricService, times(1)).findAllBy(emptyMap())
    }

    @Test
    fun `should accept query parameters when fetching metrics`() {
        given(metricService.findAllBy(params)).willReturn(metrics)
        mockMvc.perform(get("/metrics?datasourceId=1&from=2019-01-27&to=2019-03-15&campaign=1"))
                .andExpect(content().string(mapper.writeValueAsString(metrics)))
                .andExpect(status().isOk)

        verify(metricService).findAllBy(params)
    }

    @Test
    fun `should return metrics summary for a given datasource`() {
        given(metricService.getSummaryBy(emptyMap())).willReturn(summary)

        mockMvc.perform(get("/metrics/summary"))
                .andExpect(content().string(mapper.writeValueAsString(summary)))
                .andExpect(status().isOk)

        verify(metricService, times(1)).getSummaryBy(emptyMap())
    }

    @Test
    fun `should accept query parameters when fetching summary`() {
        given(metricService.getSummaryBy(params)).willReturn(summary)

        mockMvc.perform(get("/metrics/summary?datasourceId=1&from=2019-01-27&to=2019-03-15&campaign=1"))
                .andExpect(content().string(mapper.writeValueAsString(summary)))
                .andExpect(status().isOk)

        verify(metricService, times(1)).getSummaryBy(params)
    }

}

