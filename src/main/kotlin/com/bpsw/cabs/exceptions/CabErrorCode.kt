package com.bpsw.cabs.exceptions

import io.micronaut.http.HttpStatus

enum class CabErrorCode(val httpCode : HttpStatus) {
    CAB_NOT_FOUND(HttpStatus.NOT_FOUND),
    CAB_VALIDATION_EXCEPTION(HttpStatus.UNPROCESSABLE_ENTITY),
    INVALID_CAB_SEARCH_CRITERIA(HttpStatus.BAD_REQUEST)
}
