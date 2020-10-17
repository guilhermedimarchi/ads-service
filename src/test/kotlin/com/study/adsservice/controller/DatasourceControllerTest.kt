package com.study.adsservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.adsservice.model.Metric
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

@WebMvcTest(DatasourceController::class)
class DatasourceControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper : ObjectMapper

    @MockBean
    private lateinit var datasourceService: DatasourceService

    private val metrics = listOf(Metric(Instant.ofEpochSecond(1L), 3000, 100))

    @Test
    fun `should return metrics for a given datasource`() {
        val id = "1"
        given(datasourceService.getMetrics(id)).willReturn(Optional.of(metrics))

        mockMvc.perform(get("/datasources/$id/metrics"))
                .andExpect(content().string(mapper.writeValueAsString(metrics)))
                .andExpect(status().isOk)

        Mockito.verify(datasourceService, times(1)).getMetrics(id)
    }

    @Test
    fun `should return not found for unkown datasource`() {
        val id = "unknown"
        given(datasourceService.getMetrics(id)).willReturn(Optional.empty())

        mockMvc.perform(get("/datasources/$id/metrics"))
                .andExpect(status().isNotFound)

        Mockito.verify(datasourceService, times(1)).getMetrics(id)
    }




}

