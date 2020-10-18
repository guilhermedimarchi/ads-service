package com.study.adsservice.model

import java.time.Instant

data class MetricDTO(
        val dateTime: Instant,
        val impressions: Int,
        val clicks: Int
)
