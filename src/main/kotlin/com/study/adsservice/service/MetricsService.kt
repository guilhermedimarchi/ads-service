package com.study.adsservice.service



import com.study.adsservice.model.Metric
import com.study.adsservice.model.Summary
import org.springframework.stereotype.Service

@Service
class MetricsService() {

    fun getMetricsByDatasourceId(dsId: String, params: Map<String, String> = emptyMap()): List<Metric> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getMetricsSummaryByDatasourceId(dsId: String, params: Map<String, String> = emptyMap()): Summary {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getMetricsByCampaignId(campaignId: String): List<Metric> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getMetricsSummaryByCampaignId(campaignId: String): Summary {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
