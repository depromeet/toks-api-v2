package com.toks.api.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val status: HttpStatus, val description: String) {
    /** Common Error Code */
    INVALID_INPUT_VALUE_ERROR(HttpStatus.BAD_REQUEST, "input is invalid value"),
    INVALID_TYPE_VALUE_ERROR(HttpStatus.BAD_REQUEST, "invalid type value"),
    METHOD_NOT_ALLOWED_ERROR(HttpStatus.METHOD_NOT_ALLOWED, "Method type is invalid"),
    INVALID_MEDIA_TYPE_ERROR(HttpStatus.BAD_REQUEST, "invalid media type"),

    /** User Error Code */
    USERNAME_ALREADY_EXISTS_ERROR(HttpStatus.BAD_REQUEST, "이미 존재하는 username 입니다."),
    NICKNAME_ALREADY_EXISTS_ERROR(HttpStatus.BAD_REQUEST, "이미 존재하는 nickname 입니다."),
    USER_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),

    /** Auth Error Code */
    AUTH_TOKEN_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "토큰을 찾지 못했습니다."),
    LOGIN_FAIL_ERROR(HttpStatus.UNAUTHORIZED, "로그인을 실패했습니다."),
    TOKEN_INVALID_ERROR(HttpStatus.BAD_REQUEST, "잘못된 토큰 정보입니다."),
    PERMISSION_ERROR(HttpStatus.FORBIDDEN, "권한이 없습니다"),

    /** Survey Error Code */
    SURVEY_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "설문지를 찾을 수 없습니다."),
    SURVEY_METADATA_ALREADY_EXISTS_ERROR(HttpStatus.BAD_REQUEST, "설문 데이터가 이미 존재합니다."),

    /** Action Log Error Code */
    ACTION_LOG_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "액션 로그를 찾을 수 없습니다."),

    /** Image Client Error Code */
    IMAGE_CLIENT_UPLOAD_ERROR(HttpStatus.BAD_REQUEST, "이미지 업로드 중 오류가 발생했습니다."),

    /** Terms Error Code */
    TERMS_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "약관을 찾을 수 없습니다."),

    /** Notice Error Code */
    NOTICE_METADATA_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "알림 템플릿을 찾을 수 없습니다."),
    NOTICE_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "알림을 찾을 수 없습니다."),

    /** Suggest Error Code */
    SUGGEST_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "suggest를 찾을 수 없습니다."),
    ;
}
