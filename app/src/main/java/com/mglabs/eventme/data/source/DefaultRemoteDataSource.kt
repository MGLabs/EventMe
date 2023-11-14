package com.mglabs.eventme.data.source

import com.mglabs.eventme.data.model.Event
import javax.inject.Inject

class DefaultRemoteDataSource @Inject constructor() : RemoteDataSource {
    override suspend fun getEvents(): List<Event> {
        TODO("Not yet implemented")
    }

    override suspend fun addEvent(event: Event): Event {
        TODO("Not yet implemented")
    }
}