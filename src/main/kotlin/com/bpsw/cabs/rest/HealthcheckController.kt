package com.bpsw.cabs.rest

import com.bpsw.cabs.utils.TimeUtils
import com.bpsw.cabs.view.CabRep
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces

@Controller("/healthcheck")
public class HealthcheckController {

    @Get(uri = "/")
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun healthcheck(): HttpResponse<String> {
        return HttpResponse.ok(TimeUtils.getCurrentDatetimeFormatted())
    }
}
