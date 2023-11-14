package com.mglabs.eventme.data.source

import com.mglabs.eventme.data.model.Event
import com.mglabs.eventme.data.repository.WorkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface LocalDataSource {
    fun getEventFlow(eventId: String): Flow<WorkResult<Event?>>
    fun getEventsFlow(): Flow<WorkResult<List<Event>>>
    fun setEvents(events: List<Event>)
    fun addEvent(event: Event)
}