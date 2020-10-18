package com.study.adsservice.service



import com.study.adsservice.domain.Metric
import com.study.adsservice.model.MetricDTO
import com.study.adsservice.model.Summary
import com.study.adsservice.repository.MetricRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class MetricService(private val repository: MetricRepository) {

    private final val filterMap = mapOf(
            "datasourceId" to this::withDatasourceId,
            "campaignId" to this::withCampaignId,
            "from" to this::from,
            "until" to this::until
    )

    fun getMetricsByDatasourceId(dsId: String, params: Map<String, String> = emptyMap()): List<MetricDTO> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getMetricsSummaryByDatasourceId(dsId: String, params: Map<String, String> = emptyMap()): Summary {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getMetricsByCampaignId(campaignId: String): List<MetricDTO> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getMetricsSummaryByCampaignId(campaignId: String): Summary {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun withDatasourceId(id : Long) = Specification<Metric> {
        root, _, cb -> cb.equal(root.get<Long>(Metric::datasource.name), id)
    }

    fun withCampaignId(id : Long) = Specification<Metric> {
        root, _, cb -> cb.equal(root.get<Long>(Metric::campaign.name), id)
    }

    fun from(date: LocalDate) = Specification<Metric> {
        root, _, cb -> cb.greaterThanOrEqualTo(root.get<LocalDate>(Metric::daily.name), date)
    }

    fun until(date: LocalDate) = Specification<Metric> {
        root, _, cb -> cb.lessThanOrEqualTo(root.get<LocalDate>(Metric::daily.name), date)
    }

    fun findAllBy(params: Map<String, Any>): List<Metric> {
        var filters : Specification<Metric>? = null

        for(param in params) {
            val filter = filterMap[param.key] ?: continue
            if(filters == null) {
                filters = filter.call(param.value)
            } else {
                filters = filters.and(filter.call(param.value))
            }
        }

        return repository.findAll(filters)
    }
}
