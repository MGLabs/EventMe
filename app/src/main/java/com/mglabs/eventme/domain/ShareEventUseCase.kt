package com.mglabs.eventme.domain

import com.mglabs.eventme.data.model.Event
import com.mglabs.eventme.data.repository.EventRepository
import javax.inject.Inject


class ShareEventUseCase @Inject constructor(
    private val repository: EventRepository
) {
    suspend operator fun invoke(event: Event) {
        repository.shareEvent(event)
    }
}