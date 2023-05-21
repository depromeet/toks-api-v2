package com.toks.api.domain.actionlog

import com.toks.api.common.domain.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "system_action_log")
class SystemActionLog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1L,

    @Column(name = "ip_address")
    val ipAddress: String? = null,

    val path: String? = null,

    @Column(name = "http_method")
    val httpMethod: String? = null,

    @Column(name = "user_agent")
    val userAgent: String? = null,

    val host: String? = null,

    val referer: String? = null
) : BaseEntity()
