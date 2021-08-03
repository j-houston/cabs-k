package com.bpsw.cabs.rest

import com.bpsw.cabs.handlers.ICabHandler
import com.bpsw.cabs.view.CabRep
import com.bpsw.cabs.view.LatLongRep
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import javax.inject.Inject

@Controller("/api/v1/cabs")
public class CabsController {

    @Inject
    lateinit var cabHandler: ICabHandler

    @Get(uri = "/{cabId}")
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun getCab(cabId: String): HttpResponse<CabRep> {
        val cabRep : CabRep = cabHandler.getCab(id = cabId)
        return HttpResponse.ok(cabRep)
    }

    @Post(uri = "/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun createNewCab(@Body initialLocation : LatLongRep): HttpResponse<CabRep> {
        val cabRep : CabRep = cabHandler.createNewCab(initialLocation = initialLocation)
        return HttpResponse.created(cabRep)
    }
}
