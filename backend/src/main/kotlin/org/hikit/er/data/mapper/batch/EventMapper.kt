package org.hikit.er.data.mapper.batch

import org.hikit.common.data.mapper.MultiPointCoords2D
import org.hikit.er.data.Event
import org.hikit.er.data.mapper.DateTimeMapper
import org.openapitools.model.EventResponseData
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EventMapper @Autowired constructor(private val dateTimeMapper: DateTimeMapper) {
    private val logger: Logger = LoggerFactory.getLogger(EventMapper::class.java)

    fun map(erp: EventResponseData): Event? {
        return try {
            Event(
                _id = "",
                remoteId = erp.id.toString(),
                title = erp.title,
                description = erp.description,
                coordinates = MultiPointCoords2D(
                    erp.locations.map { loc ->
                        listOf(loc.lng.toDouble(),
                                loc.lat.toDouble())
                    }
                )
            )
        } catch (e: Exception) {
            logger.warn("Could not map Event. Error: ${e.message}")
            null
        }
    }
}