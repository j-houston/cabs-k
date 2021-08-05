package com.bpsw.cabs.handlers.impl

import com.bpsw.cabs.exceptions.CabNotFoundException
import com.bpsw.cabs.handlers.ICabHandler
import com.bpsw.cabs.model.CabModel
import com.bpsw.cabs.model.CabTable
import com.bpsw.cabs.view.CabRep
import com.bpsw.cabs.view.CabsPageRep
import com.bpsw.cabs.view.CabsSearchCriteriaRep
import com.bpsw.cabs.view.LatLongRep
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import javax.inject.Singleton

@Singleton
class CabHandlerImpl : ICabHandler {

    companion object {
        const val PAGE_SIZE : Int = 5
    }

    override fun createNewCab(initialLocation: LatLongRep): CabRep {
        val newCabModel: CabModel = transaction {
            addLogger(StdOutSqlLogger)

            CabModel.new(UUID.randomUUID()) {
                latitude = initialLocation.latitude
                longitude = initialLocation.longitude
            }
        }

        return modelToRep(cabModel = newCabModel)
    }

    override fun destroyAllCabs() : Int {
        return transaction {
            addLogger(StdOutSqlLogger)
            CabTable.deleteAll()
        }
    }

    override fun destroyCab(id: String) {
        transaction {
            addLogger(StdOutSqlLogger)

            val foundCab: CabModel? = CabModel.findById(UUID.fromString(id))
            foundCab?.delete()
        }
    }

    override fun getCab(id: String): CabRep {
        val foundCab: CabModel = transaction {
            addLogger(StdOutSqlLogger)

            CabModel.findById(UUID.fromString(id))
        } ?: throw CabNotFoundException(message = "Could not find cab at ID $id")

        return modelToRep(cabModel = foundCab)
    }

    override fun modelToRep(cabModel: CabModel): CabRep {
        return CabRep(
            id = cabModel.id.toString(),
            latitude = cabModel.latitude,
            longitude = cabModel.longitude
        )
    }

    override fun searchCabs(searchCriteria: CabsSearchCriteriaRep): CabsPageRep {
        val cabsPage: CabsPageRep = transaction {
            addLogger(StdOutSqlLogger)

            val offset: Long = (PAGE_SIZE * searchCriteria.page).toLong()
            val foundIter: SizedIterable<CabModel> = CabModel.all().limit(n = PAGE_SIZE, offset = offset)
            val foundList: List<CabRep> = foundIter.map { modelToRep(cabModel = it) }

            val totalCount: Int = CabModel.count().toInt()

            CabsPageRep(
                cabs = foundList,
                page = searchCriteria.page,
                totalCount = totalCount
            )
        }

        return cabsPage
    }
}
