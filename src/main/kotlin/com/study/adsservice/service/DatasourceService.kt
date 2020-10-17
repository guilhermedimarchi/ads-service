package com.study.adsservice.service


import com.study.adsservice.model.Datasource
import com.study.adsservice.model.Metric
import com.study.adsservice.model.Summary
import org.springframework.stereotype.Service
import java.util.*

@Service
class DatasourceService {
    fun getMetrics(s: String): Optional<List<Metric>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getMetricsSummary(id: String): Optional<Summary> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun findAll(): List<Datasource> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun findById(id: String): Optional<Datasource> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
