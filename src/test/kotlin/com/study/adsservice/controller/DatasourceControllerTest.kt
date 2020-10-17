package com.study.adsservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.adsservice.model.Datasource
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
import java.util.*

@WebMvcTest(DatasourceController::class)
class DatasourceControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper : ObjectMapper

    @MockBean
    private lateinit var datasourceService: DatasourceService

    private val twitterDs = Datasource("1", "Twitter")

    @Test
    fun `should return list of existing datasources`() {
        val ds = listOf(twitterDs)
        given(datasourceService.findAll()).willReturn(ds)

        mockMvc.perform(get("/datasources"))
                .andExpect(status().isOk)
                .andExpect(content().string(mapper.writeValueAsString(ds)))

        Mockito.verify(datasourceService, times(1)).findAll()
    }

    @Test
    fun `should return datasource if id is valid`() {
        val id = "1"
        given(datasourceService.findById(id)).willReturn(Optional.of(twitterDs))

        mockMvc.perform(get("/datasources/$id"))
                .andExpect(status().isOk)
                .andExpect(content().string(mapper.writeValueAsString(twitterDs)))

        Mockito.verify(datasourceService, times(1)).findById(id)
    }

    @Test
    fun `should return not found if id is unknown`() {
        val unknownId = "unknown"
        given(datasourceService.findById(unknownId)).willReturn(Optional.empty())

        mockMvc.perform(get("/datasources/$unknownId"))
                .andExpect(status().isNotFound)

        Mockito.verify(datasourceService, times(1)).findById(unknownId)
    }

}

