package com.study.adsservice.service

import com.study.adsservice.domain.Datasource
import com.study.adsservice.repository.DatasourceRepository
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
class DatasourceServiceTest {

    @Mock
    private lateinit var repository: DatasourceRepository

    private lateinit var service : DatasourceService

    @BeforeEach
    fun setup() {
        service = DatasourceService(repository)
    }

    @Test
    fun `should find all datasources`() {
        given(repository.findAll()).willReturn(listOf(Datasource(1L, "Dummy")))

        val datasources = service.findAll()

        assertThat(datasources.size).isEqualTo(1)
        assertThat(datasources.first().id).isEqualTo("1")
        assertThat(datasources.first().name).isEqualTo("Dummy")
        verify(repository, times(1)).findAll()
    }

    @Test
    fun `should return datasource if id exists`() {
        given(repository.findById(1L)).willReturn(Optional.of(Datasource(1L, "Dummy")))

        val datasource = service.findById(1L).get()

        assertThat(datasource.id).isEqualTo("1")
        assertThat(datasource.name).isEqualTo("Dummy")
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
