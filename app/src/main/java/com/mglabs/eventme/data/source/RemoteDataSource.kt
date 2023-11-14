package com.mglabs.eventme.data.source

import com.mglabs.eventme.data.model.Event
import javax.inject.Singleton

@Singleton
interface RemoteDataSource {
    suspend fun getEvents(): List<Event>
    suspend fun addEvent(event: Event): Event
}