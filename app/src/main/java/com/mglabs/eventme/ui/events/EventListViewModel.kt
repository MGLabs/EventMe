package com.mglabs.eventme.ui.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mglabs.eventme.data.model.Event
import com.mglabs.eventme.data.repository.EventRepository
import com.mglabs.eventme.data.repository.WorkResult
import com.mglabs.eventme.domain.AddEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class EventListUiState(
    val eventList: List<Event> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)

@HiltViewModel
class EventListViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val addEventUseCase: AddEventUseCase
) : ViewModel() {
    private val events = eventRepository.getEventsFlow()

    //How many things are we waiting for to load?
    // (pg 368 Exploring Android book)
    private val numLoadingItems = MutableStateFlow(0)

    val uiState = combine(events, numLoadingItems) {
            events, numLoadingItems ->
        when (events) {
            // we map the list of events (incapsulati in un workResult) in a UiState
            is WorkResult.Error -> EventListUiState(isError = true)
            is WorkResult.Loading -> EventListUiState(isLoading = true)
            is WorkResult.Success -> EventListUiState(
                eventList = events.data,
                isLoading = numLoadingItems > 0
            )
        }
    }.stateIn(  // e poi in uno stateFlow
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = EventListUiState(isLoading = true)
    )

    // Add sample events
    fun addSampleEvents() {
        viewModelScope.launch {
            val events = arrayOf(
                Event(eventId = "4", title = "event 4"),
                Event(eventId = "5", title = "event 5"),
                Event(eventId = "6", title = "event 6"),
            )
            events.forEach { addEventUseCase(it) }
        }
    }

    // Delete event
    fun deleteEvent(eventId: String) {
        viewModelScope.launch {
            eventRepository.deleteEvent(eventId)
        }
    }

    // Refresh Event list
    fun refreshEvents() {
        viewModelScope.launch {
            eventRepository.refreshEvents()
        }
    }

    // Share event


}