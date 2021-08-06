package com.bpsw.cabs

import com.bpsw.cabs.model.DatabaseFactory
import org.jetbrains.exposed.sql.Database
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class AbstractDataTest {
    private lateinit var db: Database

    @BeforeAll
    fun connectDB() {
        db = DatabaseFactory.init()
    }

    @BeforeEach
    fun resetDB() {
        DatabaseFactory.reset(drop = true)
    }

}
