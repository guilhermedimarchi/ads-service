package com.study.adsservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.adsservice.model.DatasourceDTO
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
import java.util.*

@WebMvcTest(DatasourceController::class)
class DatasourceControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper : ObjectMapper

    @MockBean
    private lateinit var datasourceService: DatasourceService

    private val twitterDs = DatasourceDTO("1", "Twitter")

    @Test
    fun `should return list of datasources`() {
        val ds = listOf(twitterDs)
        given(datasourceService.findAll()).willReturn(ds)

        mockMvc.perform(get("/datasources"))
                .andExpect(status().isOk)
                .andExpect(content().string(mapper.writeValueAsString(ds)))

        verify(datasourceService, times(1)).findAll()
    }

    @Nested
    inner class GivenDatasourceExists {
        private val id = 1L

        @BeforeEach
        fun setup() {
            given(datasourceService.findById(id)).willReturn(Optional.of(twitterDs))
        }

        @Test
        fun `should return datasource if id is valid`() {
            mockMvc.perform(get("/datasources/$id"))
                    .andExpect(status().isOk)
                    .andExpect(content().string(mapper.writeValueAsString(twitterDs)))
        }

        @Test
        fun `should return list of campaigns for the datasource`() {
            mockMvc.perform(get("/datasources/$id/campaigns"))
                    .andExpect(status().isOk)
                    .andExpect(content().string(mapper.writeValueAsString(twitterDs.campaigns)))
        }
    }

    @Nested
    inner class GivenDatasourceDoesNotExists {
        private val unknownId = -1L

        @BeforeEach
        fun setup() {
            given(datasourceService.findById(unknownId)).willReturn(Optional.empty())
        }

        @Test
        fun `should return not found if id is unknown`() {
            mockMvc.perform(get("/datasources/$unknownId"))
                    .andExpect(status().isNotFound)
        }

        @Test
        fun `should return not found when fetching campaigns from unknown datasource id`() {
            mockMvc.perform(get("/datasources/$unknownId/campaigns"))
                    .andExpect(status().isNotFound)
        }
    }
}

