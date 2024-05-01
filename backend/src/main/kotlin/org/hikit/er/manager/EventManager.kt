package org.hikit.er.manager

import org.hikit.er.data.Event
import org.hikit.er.data.dao.EventDao
import org.hikit.er.data.imported.EventImport
import org.hikit.er.data.mapper.EventMapperDto
import org.hikit.er.rest.EventDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class EventManager @Autowired constructor(
        private val eventDao: EventDao,
        private val eventMapper: EventMapperDto
) {
    fun upsertOnRemoteId(events: List<EventImport>): List<Event> =
            eventDao.upsertOnRemoteId(events)

    fun deleteOutdatedEntries() =
        eventDao.deleteOutdatedEntries(Date())


    fun getByIstat(istat: String, skip: Int, limit: Int): List<EventDto> =
            eventDao.getByIstatCode(istat, skip, limit)
                    .map { eventMapper.map(it) }

    fun countByIstat(istat: String): Int =
        eventDao.countByIstat(istat)

}
