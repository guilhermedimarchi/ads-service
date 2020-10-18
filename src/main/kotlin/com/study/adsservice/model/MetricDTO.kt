package com.study.adsservice.model

import com.study.adsservice.domain.Metric
import java.time.LocalDate

data class MetricDTO(
        val datasource: String,
        val campaign: String,
        val date: LocalDate,
        val impressions: Int,
        val clicks: Int
) {
    companion object {
        fun fromList(metrics: List<Metric>) : List<MetricDTO> {
            val list = mutableListOf<MetricDTO>()
            metrics.forEach {
                list.add(fromMetric(it))
            }
            return list
        }

        fun fromMetric(metric: Metric) : MetricDTO {
            return MetricDTO(
                    datasource = metric.datasource.name,
                    campaign = metric.campaign.name,
                    date = metric.daily,
                    impressions = metric.impressions,
                    clicks = metric.clicks
            )
        }

    }
}
