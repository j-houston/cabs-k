package com.bpsw.cabs

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("com.bpsw.cabs")
        .start()
}
