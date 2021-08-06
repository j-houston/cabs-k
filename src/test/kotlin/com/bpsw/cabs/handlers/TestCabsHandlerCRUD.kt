package com.bpsw.cabs.handlers

import com.bpsw.cabs.AbstractDataTest
import com.bpsw.cabs.exceptions.CabNotFoundException
import com.bpsw.cabs.view.CabRep
import com.bpsw.cabs.view.LatLongRep
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
class TestCabsHandlerCRUD : AbstractDataTest() {

    @Inject
    lateinit var cabsHandler: ICabHandler

    private fun assertCabsEqual(cab1: CabRep, cab2: CabRep) {
        Assertions.assertEquals(
            cab1.id,
            cab2.id
        )
        Assertions.assertEquals(
            cab1.latitude,
            cab2.latitude
        )
        Assertions.assertEquals(
            cab1.longitude,
            cab2.longitude
        )
    }

    @Test
    fun testGetCabNotFound() {
        try {
            cabsHandler.getCab(id="notfound")
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

        assertCabsEqual(
            cab1=newCab,
            cab2=foundCab
        )
    }
}
