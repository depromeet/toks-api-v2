package com.toks.api.domain.user

import com.toks.api.common.domain.BaseEntity
import com.toks.api.domain.user.enums.UserProvider
import com.toks.api.domain.user.enums.UserRole
import com.toks.api.domain.user.enums.UserStatus
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    val email: String,

    val nickname: String,

    @Column(name = "thumbnail_image_url")
    val thumbnailImageUrl: String,

    @Column(name = "profile_image_url")
    val profileImageUrl: String,

    @Enumerated(EnumType.STRING)
    val status: UserStatus,

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    val userRole: UserRole,

    @Enumerated(EnumType.STRING)
    val provider: UserProvider,

    @Column(name = "provider_id")
    val providerId: String,

    @Column(name = "refresh_token")
    val refreshToken: String
) : BaseEntity()
