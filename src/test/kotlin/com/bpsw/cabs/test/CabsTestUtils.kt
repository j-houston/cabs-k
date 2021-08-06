package com.bpsw.cabs.test

import com.bpsw.cabs.view.CabRep
import org.junit.jupiter.api.Assertions

class CabsTestUtils {

    companion object {

        fun assertCabsEqual(cab1: CabRep, cab2: CabRep) {
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
    }
}
