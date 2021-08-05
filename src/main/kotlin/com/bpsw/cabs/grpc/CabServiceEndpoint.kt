package com.bpsw.cabs.grpc

import com.bpsw.cabs.exceptions.BaseCabsException
import com.bpsw.cabs.handlers.ICabHandler
import com.bpsw.cabs.view.CabRep
import com.bpsw.cabs.view.LatLongRep
import io.grpc.StatusException
import io.grpc.stub.StreamObserver
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CabServiceEndpoint : CabsServiceGrpc.CabsServiceImplBase() {

    @Inject
    lateinit var cabsHandler : ICabHandler

    override fun createNewCab(request: CreateNewCabRequest?, responseObserver: StreamObserver<GetCabReply>?) {
        val initialLocation : LatLongRep = LatLongRep(
            request!!.latitude,
            request.longitude
        )
        val foundCab : CabRep = cabsHandler.createNewCab(initialLocation=initialLocation)
        val reply: GetCabReply = cabRepToCabReply(cabRep=foundCab)
        responseObserver!!.onNext(reply)
        responseObserver.onCompleted()
    }

    override fun destroyAllCabs(request: Empty?, responseObserver: StreamObserver<DestroyAllCabsReply>?) {
        val deleted : Int = cabsHandler.destroyAllCabs()
        val destroyAllReply : DestroyAllCabsReply = DestroyAllCabsReply
            .newBuilder()
            .setCount(deleted)
            .build()
        responseObserver!!.onNext(destroyAllReply)
        responseObserver.onCompleted()
    }

    override fun destroyCab(request: GetCabRequest?, responseObserver: StreamObserver<Empty>?) {
        val cabId = request!!.id
        cabsHandler.destroyCab(id = cabId)
        responseObserver!!.onNext(
            Empty
                .newBuilder()
                .build()
        )
        responseObserver.onCompleted()
    }

    override fun getCab(request: GetCabRequest?, responseObserver: StreamObserver<GetCabReply>?) {
        try {
            val cabId = request!!.id
            val foundCab : CabRep = cabsHandler.getCab(id=cabId)
            val reply: GetCabReply = cabRepToCabReply(cabRep=foundCab)
            responseObserver!!.onNext(reply)
            responseObserver.onCompleted()

        } catch (e: BaseCabsException) {
            throw StatusException(e.code.grpcStatus)
        }
    }

    fun cabRepToCabReply(cabRep : CabRep) : GetCabReply {
        return GetCabReply
            .newBuilder()
            .setId(cabRep.id)
            .setLatitude(cabRep.latitude)
            .setLongitude(cabRep.longitude)
            .build()
    }
}
