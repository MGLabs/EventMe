package com.mglabs.eventme.ui.addeditevent

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mglabs.eventme.data.model.Event
import com.mglabs.eventme.data.repository.EventRepository
import com.mglabs.eventme.data.repository.WorkResult
import com.mglabs.eventme.domain.AddEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddEditEventUiState(
    val eventTitle: String = "",
    val isLoading: Boolean = false,
    val isEventSaved: Boolean = false
)

@HiltViewModel
class AddEditEventViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val addEventUseCase: AddEventUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val eventId: String? =
        savedStateHandle.get<String>("eventId")
        //savedStateHandle[EventsDestinationsArgs.EVENT_ID_ARG] // io ho corretto con savedStateHandle.get<String>("homeId")

    private val _uiState = MutableStateFlow(AddEditEventUiState())
    val uiState: StateFlow<AddEditEventUiState> = _uiState.asStateFlow()

    init {
        if (eventId != null) {
            loadEvent(eventId)
        }
    }

    // carica l'attuale event
    private fun loadEvent(eventId: String) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = eventRepository.getEventFlow(eventId)
                .first() // se non metti first(), WorkResult.Success si lamenta
            if (result !is WorkResult.Success || result.data == null) {
                _uiState.update { it.copy(isLoading = false) }
            } else {
                val event = result.data
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        eventTitle = event.title
                    )
                }
            }
        }

    }

    // Salva l'evento
    fun saveEvent() {
        viewModelScope.launch {
            addEventUseCase(Event(eventId = eventId, title = _uiState.value.eventTitle))
            _uiState.update { it.copy(isEventSaved = true) }
        }
    }
}