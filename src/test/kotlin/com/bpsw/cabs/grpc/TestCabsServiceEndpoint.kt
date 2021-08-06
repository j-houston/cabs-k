package com.bpsw.cabs.grpc

import com.bpsw.cabs.AbstractDataTest
import com.bpsw.cabs.test.CabsTestUtils
import com.bpsw.cabs.view.CabRep
import com.bpsw.cabs.view.LatLongRep
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import io.micronaut.grpc.server.GrpcServerChannel
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import javax.inject.Inject


@MicronautTest
class TestCabsServiceEndpoint : AbstractDataTest() {

    @Inject
    lateinit var stub : CabsServiceGrpc.CabsServiceBlockingStub

    @Test
    fun testCreateGetCab() {
        val createRequest : CreateNewCabRequest = CreateNewCabRequest
            .newBuilder()
            .setLatitude(1.1f)
            .setLongitude(1.2f)
            .build()

        val createReply : GetCabReply = stub.createNewCab(createRequest)
        Assertions.assertNotNull(createReply)

        val newCab : CabRep = CabRep(
            createReply.id,
            createReply.latitude,
            createReply.longitude
        )
        Assertions.assertNotNull(newCab.id)
        Assertions.assertEquals(
            1.1f,
            newCab.latitude
        )
        Assertions.assertEquals(
            1.2f,
            newCab.longitude
        )

        val getRequest : GetCabRequest = GetCabRequest
            .newBuilder()
            .setId(newCab.id)
            .build()

        val getReply : GetCabReply = stub.getCab(getRequest)
        Assertions.assertNotNull(getReply)

        val foundCab : CabRep = CabRep(
            getReply.id,
            getReply.latitude,
            getReply.longitude
        )

        CabsTestUtils.assertCabsEqual(
            cab1=newCab,
            cab2=foundCab
        )
    }

    @Factory
    internal class Clients {
        @Bean
        fun blockingStub(
            @GrpcChannel(GrpcServerChannel.NAME) channel: ManagedChannel?
        ): CabsServiceGrpc.CabsServiceBlockingStub {
            return CabsServiceGrpc.newBlockingStub(channel)
        }
    }

}
