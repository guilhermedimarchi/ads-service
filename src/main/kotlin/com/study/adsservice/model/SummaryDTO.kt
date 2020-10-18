package com.study.adsservice.model

data class SummaryDTO(
        val totalClicks: Long,
        val totalImpressions: Long
) {
    val CTR by lazy {
        (this.totalClicks / this.totalImpressions.toDouble())
    }
}
