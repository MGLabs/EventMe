package com.mglabs.eventme.ui.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mglabs.eventme.R
import com.mglabs.eventme.data.model.Event
import com.mglabs.eventme.databinding.EventListItemBinding

class EventAdapter(private val eventList: List<Event>, private val listener: ListItemListener) :
    RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.event_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventAdapter.ViewHolder, position: Int) {
        val event = eventList[position]

       with(holder.binding) {
            eventTitleTv.text = event.title
           // eventLocationTv.text = event.
            root.setOnClickListener {
                // I'm notifying the listener class (the fragment) that the user has clicked on that element
                listener.editEvent(event.eventId?: "Evento non trovato")
            }
       }
    }

    override fun getItemCount(): Int {
        return eventList.size
    }


    // ViewHolder
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Voglio riferirmi al layout della row list_item.xml
        val binding = EventListItemBinding.bind(itemView)
    }

    //Get the click event on the edit icon
    interface ListItemListener {
        fun editEvent(eventId: String)
    }
}