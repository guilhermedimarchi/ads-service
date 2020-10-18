package com.study.adsservice.domain

import javax.persistence.*

@Entity
@Table(name = "datasource")
data class Datasource(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val name: String
)
{
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "datasource_campaign",
               joinColumns = [JoinColumn(name = "datasource_id")],
               inverseJoinColumns = [JoinColumn(name = "campaign_id")])
    val campaigns: List<Campaign> = mutableListOf()
}





