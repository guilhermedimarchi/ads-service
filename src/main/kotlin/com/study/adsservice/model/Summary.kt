package com.study.adsservice.model

data class Summary(
        val totalClicks: Long,
        val totalImpressions: Long
) {
    val CTR by lazy {
        (this.totalClicks / this.totalImpressions.toDouble())
    }
}
