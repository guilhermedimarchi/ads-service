package com.study.adsservice.model

import com.study.adsservice.domain.Datasource

data class DatasourceDTO(
        val id: String,
        val name: String,
        val campaigns: List<CampaignDTO> = mutableListOf()
) {
    companion object {
        fun fromDatasource(datasource : Datasource) : DatasourceDTO {
            return DatasourceDTO(
                    id = datasource.id.toString(),
                    name = datasource.name
            )
        }
        fun fromList(datasources: List<Datasource>) : List<DatasourceDTO> {
            val list = mutableListOf<DatasourceDTO>()
            datasources.forEach {
                list.add(fromDatasource(it))
            }
            return list
        }
    }
}
