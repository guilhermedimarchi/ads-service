package com.study.adsservice.repository.queryspec

import com.study.adsservice.domain.Metric
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MetricQueryFilter(private val params : Map<String, String>) {

    private val filterMap = mapOf(
            "datasourceId" to this::withDatasourceId,
            "campaignId" to this::withCampaignId,
            "from" to this::from,
            "until" to this::until
    )

    fun build(): Specification<Metric>? {
        var filters : Specification<Metric>? = null

        for(param in params) {
            val filter = filterMap[param.key] ?: continue
            if(filters == null) {
                filters = filter.call(param.value)
            } else {
                filters = filters.and(filter.call(param.value))
            }
        }
        return filters
    }

    fun withDatasourceId(id : String) = Specification<Metric> {
        root, _, cb -> cb.equal(root.get<Long>(Metric::datasource.name), id.toLong())
    }

    fun withCampaignId(id : String) = Specification<Metric> {
        root, _, cb -> cb.equal(root.get<Long>(Metric::campaign.name), id.toLong())
    }

    fun from(date: String) = Specification<Metric> {
        root, _, cb -> cb.greaterThanOrEqualTo(root.get<LocalDate>(Metric::daily.name), localDateFromString(date))
    }

    fun until(date: String) = Specification<Metric> {
        root, _, cb -> cb.lessThanOrEqualTo(root.get<LocalDate>(Metric::daily.name), localDateFromString(date))
    }

    private fun localDateFromString(str : String) = LocalDate.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd"))

}
