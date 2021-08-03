package com.bpsw.cabs

import com.bpsw.cabs.model.DatabaseFactory
import io.grpc.protobuf.services.ProtoReflectionService
import io.micronaut.runtime.Micronaut.*

fun main(args: Array<String>) {

    DatabaseFactory.init()

    build()
        .args(*args)
        .packages("com.bpsw.cabs")
        .singletons(ProtoReflectionService.newInstance())
        .start()
}
