package com.toks.api.domain.actionlog

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SystemActionLogRepository : JpaRepository<SystemActionLog, Long>
