package com.bpsw.cabs.exceptions

import io.grpc.Status
import io.micronaut.http.HttpStatus

enum class CabErrorCode(
    val httpCode : HttpStatus,
    val grpcStatus : Status
) {
    CAB_NOT_FOUND(HttpStatus.NOT_FOUND, Status.NOT_FOUND),
    CAB_VALIDATION_EXCEPTION(HttpStatus.UNPROCESSABLE_ENTITY, Status.FAILED_PRECONDITION),
    INVALID_CAB_SEARCH_CRITERIA(HttpStatus.BAD_REQUEST, Status.INVALID_ARGUMENT)
}
