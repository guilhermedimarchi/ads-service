package com.study.adsservice.service



import com.study.adsservice.model.MetricDTO
import com.study.adsservice.model.Summary
import com.study.adsservice.repository.MetricRepository
import com.study.adsservice.repository.queryspec.MetricQueryFilter
import org.springframework.stereotype.Service

@Service
class MetricService(private val repository: MetricRepository) {

    fun findAllBy(params: Map<String, String>): List<MetricDTO> {
        val metrics = repository.findAll(MetricQueryFilter(params).build())
        return MetricDTO.fromList(metrics)
    }

    fun getSummaryBy(params: Map<String, String>): Summary {
        val metrics = repository.findAll(MetricQueryFilter(params).build())
        var totalClicks = 0L
        var totalImpressions = 0L

        for(m in metrics) {
            totalClicks += m.clicks
            totalImpressions += m.impressions
        }

        return Summary(totalClicks,totalImpressions)
    }
}
