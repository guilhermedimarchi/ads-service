package com.study.adsservice.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class MetricsServiceTest {

    @Autowired
    private lateinit var metricService: MetricService

    @Test
    fun `should return metrics given a datasourceId`() {
        val params = mapOf("datasourceId" to "1")
        val metrics = metricService.findAllBy(params)
        assertThat(metrics.size).isEqualTo(10)
    }

    @Test
    fun `should return metrics given a campaignId`() {
        val params = mapOf("campaignId" to "1")
        val metrics = metricService.findAllBy(params)
        assertThat(metrics.size).isEqualTo(5)
    }

    @Test
    fun `should return metrics given a start date`() {
        val params = mapOf("from" to "2019-06-04")
        val metrics = metricService.findAllBy(params)
        assertThat(metrics.size).isEqualTo(6)
    }

    @Test
    fun `should return metrics until a given date`() {
        val params = mapOf("until" to "2019-06-03")
        val metrics = metricService.findAllBy(params)
        assertThat(metrics.size).isEqualTo(9)
    }

    @Test
    fun `should return metrics given multiple filters`() {
        val params = mapOf(
                "datasourceId" to "1",
                "campaignId" to "1",
                "from" to "2019-06-02",
                "until" to "2019-06-04"
        )
        val metrics = metricService.findAllBy(params)
        assertThat(metrics.size).isEqualTo(3)
    }

    @Test
    fun `Should return summary`() {
        val params = mapOf("datasourceId" to "1")
        val summary = metricService.getSummaryBy(params)
        assertThat(summary.totalClicks).isEqualTo(88)
        assertThat(summary.totalImpressions).isEqualTo(7500)
        assertThat(summary.CTR).isEqualTo(88/7500.0)
    }


}
