package com.bpsw.cabs.handlers

import com.bpsw.cabs.model.CabModel
import com.bpsw.cabs.view.CabRep
import com.bpsw.cabs.view.CabsPageRep
import com.bpsw.cabs.view.CabsSearchCriteriaRep
import com.bpsw.cabs.view.LatLongRep

interface ICabHandler {

    fun createNewCab(initialLocation: LatLongRep) : CabRep

    fun destroyAllCabs() : Int

    fun destroyCab(id: String)

    fun getCab(id: String) : CabRep

    fun modelToRep(cabModel: CabModel) : CabRep

    fun searchCabs(searchCriteria : CabsSearchCriteriaRep) : CabsPageRep
}
