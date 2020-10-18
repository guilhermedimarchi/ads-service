package com.study.adsservice.repository

import com.study.adsservice.domain.Datasource
import org.springframework.data.jpa.repository.JpaRepository

interface DatasourceRepository : JpaRepository<Datasource, Long> {

}
