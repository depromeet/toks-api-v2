package com.toks.api.domain.user.enums

enum class UserRole(val role: String, val description: String) {
    ANONYMOUS("ANONYMOUS", "비로그인"),
    USER("USER", "유저권한"),
    ADMIN("ADMIN", "어드민권한"),
    ;
}
