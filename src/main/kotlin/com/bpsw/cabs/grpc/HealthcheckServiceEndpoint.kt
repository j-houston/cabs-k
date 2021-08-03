package com.bpsw.cabs.grpc

import com.bpsw.cabs.utils.TimeUtils
import io.grpc.stub.StreamObserver
import javax.inject.Singleton

@Singleton
class HealthcheckServiceEndpoint : HealthcheckServiceGrpc.HealthcheckServiceImplBase() {

    override fun healthcheck(request: HealthcheckRequest?, responseObserver: StreamObserver<HealthcheckReply>?) {
        val formattedTime = TimeUtils.getCurrentDatetimeFormatted()
        val message = "$formattedTime - Hello: ${request!!.name}"
        val reply = HealthcheckReply.newBuilder().setMessage(message).build()
        responseObserver!!.onNext(reply)
        responseObserver.onCompleted()
    }
}
