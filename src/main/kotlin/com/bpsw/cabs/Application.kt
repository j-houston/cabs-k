package com.bpsw.cabs

import com.bpsw.cabs.model.DatabaseFactory
import io.micronaut.runtime.Micronaut.*

fun main(args: Array<String>) {

    DatabaseFactory.init()

    build()
        .args(*args)
        .packages("com.bpsw.cabs")
        .start()
}
