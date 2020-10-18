package com.study.adsservice.domain

import java.time.LocalDate
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "metric")
data class Metric(

        @Id
        @GeneratedValue(strategy = IDENTITY)
        val id: Long? = null,

        @ManyToOne
        @JoinColumn(name="datasource_id")
        val datasource: Datasource,

        @ManyToOne
        @JoinColumn(name="campaign_id")
        val campaign: Campaign,

        val daily: LocalDate,
        val impressions: Int,
        val clicks: Int

)
