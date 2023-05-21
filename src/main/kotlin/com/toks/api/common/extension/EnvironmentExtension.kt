package com.toks.api.common.extension

import org.springframework.core.env.Environment

fun Environment.isProd(): Boolean {
    return this.activeProfiles.any { it.equals(EnvironmentType.PROFILE_PROD) }
}

fun Environment.isStaging(): Boolean {
    return this.activeProfiles.any { it.equals(EnvironmentType.PROFILE_STAGING) }
}

fun Environment.isTest(): Boolean {
    return this.activeProfiles.any { it.equals(EnvironmentType.PROFILE_TEST) }
}

object EnvironmentType {
    const val PROFILE_PROD = "prod"
    const val PROFILE_STAGING = "staging"
    const val PROFILE_TEST = "test"
}
