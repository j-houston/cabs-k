package com.bpsw.cabs.rest

import com.bpsw.cabs.exceptions.BaseCabsException
import io.micronaut.core.exceptions.ExceptionHandler
import io.micronaut.http.exceptions.HttpStatusException
import io.micronaut.http.annotation.Produces
import javax.inject.Singleton

@Produces
@Singleton
public class CabsExceptionHandler : ExceptionHandler<BaseCabsException> {

    override fun handle(exception: BaseCabsException?) {
        throw HttpStatusException(exception!!.code.httpCode, exception.message)
    }
}
