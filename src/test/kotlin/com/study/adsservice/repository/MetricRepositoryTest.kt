package com.study.adsservice.repository

import com.study.adsservice.domain.Campaign
import com.study.adsservice.domain.Datasource
import com.study.adsservice.domain.Metric
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate

@SpringBootTest
@ActiveProfiles("test")
class MetricRepositoryTest {

    @Autowired
    private lateinit var repo : MetricRepository

    @Test
    fun `should create metric entity`() {
        val date = LocalDate.now()
        val m = Metric(1L, Datasource(1L,"A"), Campaign(1L,"B"), date, 1000, 100)
        assertThat(m).isNotNull
        assertThat(m.id).isEqualTo(1L)
        assertThat(m.campaign).isNotNull
        assertThat(m.datasource).isNotNull
        assertThat(m.clicks).isEqualTo(100)
        assertThat(m.daily).isEqualTo(date)
        assertThat(m.impressions).isEqualTo(1000)
    }

    @Test
    fun `should return list of metrics entites`() {
        val metrics = repo.findAll()
        assertThat(metrics.size).isEqualTo(15)
    }

    @Test
    fun `Should return daily metrics by datasourceId`() {
        val metrics = repo.findByDatasourceId(1L)
        assertThat(metrics.size).isEqualTo(10)
    }

    @Test
    fun `Should return summary by datasourceId`() {
        val summary = repo.getMetricsSummaryByDatasourceId(1L)
        assertThat(summary.totalClicks).isEqualTo(88)
        assertThat(summary.totalImpressions).isEqualTo(7500)
        assertThat(summary.CTR).isEqualTo(88/7500.0)
    }
}
