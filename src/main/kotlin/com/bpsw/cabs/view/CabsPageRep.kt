package com.bpsw.cabs.view

class CabsPageRep(
    val cabs : List<CabRep>,
    val page : Int,
    val totalCount : Int
) : AbstractRep() {
}
