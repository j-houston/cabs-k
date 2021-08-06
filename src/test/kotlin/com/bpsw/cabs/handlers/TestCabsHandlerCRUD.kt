package com.bpsw.cabs.handlers

import com.bpsw.cabs.AbstractDataTest
import com.bpsw.cabs.exceptions.CabNotFoundException
import com.bpsw.cabs.test.CabsTestUtils
import com.bpsw.cabs.view.CabRep
import com.bpsw.cabs.view.LatLongRep
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*
import javax.inject.Inject

@MicronautTest
class TestCabsHandlerCRUD : AbstractDataTest() {

    @Inject
    lateinit var cabsHandler: ICabHandler

    @Test
    fun testGetCabNotFound() {
        try {
            cabsHandler.getCab(id=UUID.randomUUID().toString())
            Assertions.fail("Expected an exception")
        } catch (e: CabNotFoundException) {
            // Expected
        }
    }

    @Test
    fun testCreateGetCab() {
        val newCab : CabRep = cabsHandler.createNewCab(initialLocation = LatLongRep(
            latitude = 1.1f,
            longitude = 1.2f
        ))
        Assertions.assertNotNull(newCab)
        Assertions.assertNotNull(newCab.id)
        Assertions.assertEquals(
            1.1f,
            newCab.latitude
        )
        Assertions.assertEquals(
            1.2f,
            newCab.longitude
        )

        val foundCab : CabRep = cabsHandler.getCab(id=newCab.id!!)

        CabsTestUtils.assertCabsEqual(
            cab1=newCab,
            cab2=foundCab
        )
    }
}
