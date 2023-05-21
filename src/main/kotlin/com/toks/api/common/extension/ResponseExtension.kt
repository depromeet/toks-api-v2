package com.toks.api.common.extension

import com.toks.api.common.dto.PageResponseDto
import com.toks.api.common.dto.ResponseDto
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

/** Wrap Response Page */
fun <T> Page<T>.wrapPage() = PageResponseDto(this)

/** Wrap Response Ok */
fun <T> T.wrapOk() = ResponseEntity.ok(ResponseDto(this))

/** Wrap Response Created */
fun <T> T.wrapCreated() = ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto(this))

/** Wrap Response Void */
fun Unit.wrapVoid() = ResponseEntity.noContent()
