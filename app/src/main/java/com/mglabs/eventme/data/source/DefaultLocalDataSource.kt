package com.mglabs.eventme.data.source

import com.mglabs.eventme.data.model.Event
import com.mglabs.eventme.data.repository.WorkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultLocalDataSource @Inject constructor(): LocalDataSource{
    override fun getEventFlow(eventId: String): Flow<WorkResult<Event?>> {
        TODO("Not yet implemented")
    }

    override fun getEventsFlow(): Flow<WorkResult<List<Event>>> {
        TODO("Not yet implemented")
    }

    override fun setEvents(events: List<Event>) {
        TODO("Not yet implemented")
    }

    override fun addEvent(event: Event) {
        TODO("Not yet implemented")
    }
}