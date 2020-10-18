package com.study.adsservice.domain

import javax.persistence.*

@Entity
@Table(name = "campaign")
data class Campaign(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val name: String
)
{
    @ManyToMany(mappedBy = "campaigns", fetch = FetchType.EAGER)
    val datasources: MutableSet<Datasource> = HashSet()
}
