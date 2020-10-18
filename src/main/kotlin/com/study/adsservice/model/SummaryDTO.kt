package com.study.adsservice.model

data class SummaryDTO(
        val totalClicks: Long,
        val totalImpressions: Long
) {
    val CTR by lazy {
        if (this.totalImpressions == 0L) 0
        else (this.totalClicks / this.totalImpressions.toDouble())
    }
}
