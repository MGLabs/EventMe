package com.mglabs.eventme.data.repository

import com.mglabs.eventme.data.model.Event
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow


interface EventRepository {
    fun getEventFlow(eventId: String): Flow<WorkResult<Event?>>
    fun getEventsFlow(): Flow<WorkResult<List<Event>>>
    suspend fun refreshEvents()
    suspend fun addEvent(event: Event)
    suspend fun deleteEvent(eventId: String)

    suspend fun shareEvent(event: Event)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindEventRepository(
        defaultEventRepository: DefaultEventRepository
    ): EventRepository
}