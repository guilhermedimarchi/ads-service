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

@WebMvcTest(DatasourceMetricsController::class)
class DatasourceMetricsControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper : ObjectMapper

    @MockBean
    private lateinit var datasourceService: DatasourceService

    @MockBean
    private lateinit var metricService: MetricService

    @Nested
    inner class GivenExistingDatasource {
        private val id = 1L
        private val dummyDs = DatasourceDTO("","")
        private val metrics = listOf(MetricDTO("Facebook", "Sales", LocalDate.now(), 3000, 100))
        private val params = mapOf("datasourceId" to "1", "from" to "2019-01-27", "to" to "2019-03-15", "campaign" to "1")
        private val summary = SummaryDTO(100,10)

        @BeforeEach
        fun setup() {
            given(datasourceService.findById(id)).willReturn(Optional.of(dummyDs))
        }

        @Test
        fun `should return metrics for a given datasource`() {
            given(metricService.findAllBy(mapOf("datasourceId" to "1"))).willReturn(metrics)
            mockMvc.perform(get("/datasources/$id/metrics"))
                    .andExpect(content().string(mapper.writeValueAsString(metrics)))
                    .andExpect(status().isOk)

            verify(metricService, times(1)).findAllBy(mapOf("datasourceId" to "1"))
        }

        @Test
        fun `should accept query parameters when fetching metrics`() {
            given(metricService.findAllBy(params)).willReturn(metrics)
            mockMvc.perform(get("/datasources/$id/metrics?from=2019-01-27&to=2019-03-15&campaign=1"))
                    .andExpect(content().string(mapper.writeValueAsString(metrics)))
                    .andExpect(status().isOk)

            verify(metricService).findAllBy(params)
        }

        @Test
        fun `should return metrics summary for a given datasource`() {
            given(metricService.getSummaryBy(mapOf("datasourceId" to "1"))).willReturn(summary)

            mockMvc.perform(get("/datasources/$id/summary"))
                    .andExpect(content().string(mapper.writeValueAsString(summary)))
                    .andExpect(status().isOk)

            verify(metricService, times(1)).getSummaryBy(mapOf("datasourceId" to "1"))
        }

        @Test
        fun `should accept query parameters when fetching summary`() {
            given(metricService.getSummaryBy(params)).willReturn(summary)

            mockMvc.perform(get("/datasources/$id/summary?from=2019-01-27&to=2019-03-15&campaign=1"))
                    .andExpect(content().string(mapper.writeValueAsString(summary)))
                    .andExpect(status().isOk)

            verify(metricService, times(1)).getSummaryBy(params)
        }
    }

    @Nested
    inner class GivenNotFoundDatasource {

        private val unknownId = -1L

        @BeforeEach
        fun setup() {
            given(datasourceService.findById(unknownId)).willReturn(Optional.empty())
        }

        @Test
        fun `should return not found for unknown datasource`() {
            mockMvc.perform(get("/datasources/$unknownId/metrics"))
                    .andExpect(status().isNotFound)

            verify(metricService, times(0)).findAllBy(mapOf("datasource" to "$unknownId"))
        }

        @Test
        fun `should return not found for unknown datasource summary`() {
            mockMvc.perform(get("/datasources/$unknownId/summary"))
                    .andExpect(status().isNotFound)

            verify(metricService, times(0)).getSummaryBy(mapOf("datasource" to "$unknownId"))
        }
    }
}

