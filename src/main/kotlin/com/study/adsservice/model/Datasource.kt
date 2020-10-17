package com.study.adsservice.model

data class Datasource(
        val id: String,
        val name: String,
        val campaigns: List<Campaign> = mutableListOf()
)
