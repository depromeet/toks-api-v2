package com.toks.api.domain.image

import com.toks.api.common.domain.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "image")
class Image(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    @Column(name = "image_url")
    val imageUrl: String,

    @Column(name = "created_by")
    val createdBy: Long
) : BaseEntity()
