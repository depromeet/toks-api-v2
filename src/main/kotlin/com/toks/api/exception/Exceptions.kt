package com.toks.api.exception

open class BusinessException(
    val errorCode: ErrorCode,
    override val message: String? = errorCode.description
) : RuntimeException(message ?: errorCode.description)

/** User Exception */
class UsernameAlreadyExistsException(message: String? = null) : BusinessException(
    errorCode = ErrorCode.USERNAME_ALREADY_EXISTS_ERROR,
    message = message
)

class NicknameAlreadyExistsException(message: String? = null) : BusinessException(
    errorCode = ErrorCode.NICKNAME_ALREADY_EXISTS_ERROR,
    message = message
)

class UserNotFoundException(message: String? = null) : BusinessException(
    errorCode = ErrorCode.USER_NOT_FOUND_ERROR,
    message = message
)

/** Auth Exception */
class AuthNotFoundToken(message: String? = null) : BusinessException(
    errorCode = ErrorCode.AUTH_TOKEN_NOT_FOUND_ERROR,
    message = message
)

class LoginFailException : BusinessException(ErrorCode.LOGIN_FAIL_ERROR)

class TokenInvalidException : BusinessException(ErrorCode.TOKEN_INVALID_ERROR)

class PermissionException : BusinessException(ErrorCode.PERMISSION_ERROR)

/** Survey Exception */
class SurveyNotFoundException : BusinessException(ErrorCode.SURVEY_NOT_FOUND_ERROR)
class SurveyMetadataAlreadyExistsException : BusinessException(ErrorCode.SURVEY_METADATA_ALREADY_EXISTS_ERROR)

/** Action Log Exception */
class ActionLogNotFoundException : BusinessException(ErrorCode.ACTION_LOG_NOT_FOUND_ERROR)

/** Image Exception */
class ImageUploadException : BusinessException(ErrorCode.IMAGE_CLIENT_UPLOAD_ERROR)

/** Terms Exception */
class TermsNotFoundException : BusinessException(ErrorCode.TERMS_NOT_FOUND_ERROR)

/** Notice Exception */
class NoticeNotFoundException : BusinessException(ErrorCode.NOTICE_NOT_FOUND_ERROR)

class NoticeMetadataNotFoundException : BusinessException(ErrorCode.NOTICE_METADATA_NOT_FOUND_ERROR)

/** Suggest Exception */
class SuggestNotFoundException : BusinessException(ErrorCode.SUGGEST_NOT_FOUND_ERROR)
