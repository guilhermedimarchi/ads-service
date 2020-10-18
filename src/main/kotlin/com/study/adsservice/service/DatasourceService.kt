package com.study.adsservice.service


import com.study.adsservice.model.DatasourceDTO
import com.study.adsservice.repository.DatasourceRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class DatasourceService(private val repository: DatasourceRepository) {

    fun findAll(): List<DatasourceDTO> {
        val datasources = repository.findAll()
        return DatasourceDTO.fromList(datasources)
    }

    fun findById(id: Long): Optional<DatasourceDTO> {
        val datasource = repository.findById(id)
        if(datasource.isEmpty)
            return Optional.empty()
        return Optional.of(DatasourceDTO.fromDatasource(datasource.get()))
    }
}
