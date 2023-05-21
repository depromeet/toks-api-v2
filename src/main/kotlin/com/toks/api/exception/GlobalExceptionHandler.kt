package com.toks.api.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = mu.KotlinLogging.logger { }

    @ExceptionHandler(BusinessException::class)
    protected fun handleBusinessException(e: BusinessException): ResponseEntity<ErrorResponse> {
        logger.error { "BusinessException ${e.message}" }
        val response = ErrorResponse(e.errorCode, e)
        return ResponseEntity(response, e.errorCode.status)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    protected fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
        logger.error("HttpMessageNotReadableException", e)
        val response = ErrorResponse(ErrorCode.INVALID_INPUT_VALUE_ERROR, e)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    protected fun handleMethodArgumentTypeMismatchException(e: MethodArgumentTypeMismatchException): ResponseEntity<ErrorResponse> {
        logger.error("MethodArgumentTypeMismatchException", e)
        val response = ErrorResponse(ErrorCode.INVALID_TYPE_VALUE_ERROR, e)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    protected fun handleHttpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException?): ResponseEntity<ErrorResponse> {
        logger.error("HttpRequestMethodNotSupportedException", e)
        val response = ErrorResponse(ErrorCode.METHOD_NOT_ALLOWED_ERROR)
        return ResponseEntity(response, HttpStatus.METHOD_NOT_ALLOWED)
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException::class)
    protected fun handleHttpMediaTypeNotSupportedException(e: HttpMediaTypeNotSupportedException?): ResponseEntity<ErrorResponse> {
        logger.error("HttpMediaTypeNotSupportedException", e)
        val response = ErrorResponse(ErrorCode.INVALID_MEDIA_TYPE_ERROR)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        logger.error("MethodArgumentNotValidException", e)
        val response = ErrorResponse(ErrorCode.INVALID_INPUT_VALUE_ERROR, e.bindingResult)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }
}
