package com.study.adsservice.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ofPattern

@SpringBootTest
@ActiveProfiles("test")
class MetricsServiceTest {

    @Autowired
    private lateinit var metricService: MetricService

    @Test
    fun `should return metrics given a datasourceId`() {
        val params = mapOf("datasourceId" to 1L)
        val metrics = metricService.findAllBy(params)
        assertThat(metrics.size).isEqualTo(10)
    }

    @Test
    fun `should return metrics given a campaignId`() {
        val params = mapOf("campaignId" to 1L)
        val metrics = metricService.findAllBy(params)
        assertThat(metrics.size).isEqualTo(5)
    }

    @Test
    fun `should return metrics given a start date`() {
        val params = mapOf("from" to LocalDate.parse("2019-06-04", ofPattern("yyyy-MM-dd")))
        val metrics = metricService.findAllBy(params)
        assertThat(metrics.size).isEqualTo(6)
    }

    @Test
    fun `should return metrics until a given date`() {
        val params = mapOf("until" to LocalDate.parse("2019-06-03", ofPattern("yyyy-MM-dd")))
        val metrics = metricService.findAllBy(params)
        assertThat(metrics.size).isEqualTo(9)
    }

    @Test
    fun `should return metrics given multiple filters`() {
        val params = mapOf(
                "datasourceId" to 1L,
                "campaignId" to 1L,
                "from" to LocalDate.parse("2019-06-02", ofPattern("yyyy-MM-dd")),
                "until" to LocalDate.parse("2019-06-04", ofPattern("yyyy-MM-dd"))
        )
        val metrics = metricService.findAllBy(params)
        assertThat(metrics.size).isEqualTo(3)
    }

}
