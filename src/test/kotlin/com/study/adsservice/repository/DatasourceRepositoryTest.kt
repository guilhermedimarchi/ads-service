package com.study.adsservice.repository

import com.study.adsservice.domain.Datasource
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class DatasourceRepositoryTest {

    @Autowired
    private lateinit var repo : DatasourceRepository

    @Test
    fun `should create datasource entity`() {
        val ds = Datasource(1L, "Test")
        assertThat(ds).isNotNull
        assertThat(ds.id).isEqualTo(1L)
        assertThat(ds.name).isEqualTo("Test")
    }

    @Test
    fun `should return list of datasource entites`() {
        val datasources = repo.findAll()
        assertThat(datasources.size).isEqualTo(2)
        assertThat(datasources.first().id).isEqualTo(1L)
        assertThat(datasources.first().name).isEqualTo("Facebook Ads")
    }

    @Test
    fun `should contain list of campaigns inside a datasource`() {
        val datasources = repo.findAll().first()
        assertThat(datasources.campaigns.size).isEqualTo(2)
    }
}
