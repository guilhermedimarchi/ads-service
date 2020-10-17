package com.study.adsservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.adsservice.model.Datasource
import com.study.adsservice.model.Metric
import com.study.adsservice.model.Summary
import com.study.adsservice.service.MetricsService
import com.study.adsservice.service.DatasourceService
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

@WebMvcTest(DatasourceMetricsController::class)
class DatasourceMetricsControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper : ObjectMapper

    @MockBean
    private lateinit var datasourceService: DatasourceService

    @MockBean
    private lateinit var metricsService: MetricsService

    @Nested
    inner class GivenExistingDatasource {
        private val id = "1"
        private val dummyDs = Datasource("","")
        private val metrics = listOf(Metric(Instant.ofEpochSecond(1L), 3000, 100))

        @BeforeEach
        fun setup() {
            given(datasourceService.findById(id)).willReturn(Optional.of(dummyDs))
        }

        @Test
        fun `should return metrics for a given datasource`() {
            given(metricsService.getMetrics(id)).willReturn(metrics)
            mockMvc.perform(get("/datasources/$id/metrics"))
                    .andExpect(content().string(mapper.writeValueAsString(metrics)))
                    .andExpect(status().isOk)

            verify(metricsService, times(1)).getMetrics(id)
        }

   /*     @Test
        fun `should accept query parameters when fetching metrics`() {
            //TODO
            given(metricsService.getMetrics(id)).willReturn(metrics)
            mockMvc.perform(get("/datasources/$id/metrics?from=20190127&to=20190315&campaigns=1,2"))
                    .andExpect(content().string(mapper.writeValueAsString(metrics)))
                    .andExpect(status().isOk)
        }
    */

        @Test
        fun `should return metrics summary for a given datasource`() {
            val summary = Summary(100,10,10)
            given(metricsService.getMetricsSummary(id)).willReturn(summary)

            mockMvc.perform(get("/datasources/$id/summary"))
                    .andExpect(content().string(mapper.writeValueAsString(summary)))
                    .andExpect(status().isOk)

            verify(metricsService, times(1)).getMetricsSummary(id)
        }


    }

    @Nested
    inner class GivenNotFoundDatasource {

        private val unknownId = "unknown"

        @BeforeEach
        fun setup() {
            given(datasourceService.findById(unknownId)).willReturn(Optional.empty())
        }

        @Test
        fun `should return not found for unknown datasource`() {
            mockMvc.perform(get("/datasources/$unknownId/metrics"))
                    .andExpect(status().isNotFound)

            verify(metricsService, times(0)).getMetrics(unknownId)
        }

        @Test
        fun `should return not found for unknown datasource summary`() {
            mockMvc.perform(get("/datasources/$unknownId/summary"))
                    .andExpect(status().isNotFound)

            verify(metricsService, times(0)).getMetricsSummary(unknownId)
        }
    }

}

