package com.mglabs.eventme.data.repository

import com.mglabs.eventme.data.model.Event
import com.mglabs.eventme.data.source.LocalDataSource
import com.mglabs.eventme.data.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultEventRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
): EventRepository {


    override fun getEventFlow(eventId: String): Flow<WorkResult<Event?>> {
        return localDataSource.getEventFlow(eventId)
    }

    override fun getEventsFlow(): Flow<WorkResult<List<Event>>> {
        return localDataSource.getEventsFlow()
    }

    override suspend fun refreshEvents() {
        val events = remoteDataSource.getEvents()
        localDataSource.setEvents(events)
    }

    override suspend fun addEvent(event: Event) {
        val eventWithId = remoteDataSource.addEvent(event)
        localDataSource.addEvent(eventWithId)
    }

    override suspend fun deleteEvent(eventId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun shareEvent(event: Event) {
        TODO("Not yet implemented")
    }
}