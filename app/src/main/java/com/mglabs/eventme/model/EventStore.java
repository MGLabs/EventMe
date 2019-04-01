package com.mglabs.eventme.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.widget.Toast;

import com.mglabs.eventme.MainActivity;
import com.mglabs.eventme.R;

import java.util.ArrayList;
import java.util.List;

public class EventStore {

    private Context context;
    private static List<Event> eventList = new ArrayList<>();

    public EventStore(Context context) {
        this.context = context;
    }

    public static List<Event> getEventList() {
        return eventList;
    }

    public static void setEventList(List<Event> eventList) {
        EventStore.eventList = eventList;
    }

    public Event getEventByID(int id) {
        return eventList.get(id);
    }

    /**
     * Method for initializing the Event data from resources.
     */
    public void initializeData() {
        int[] eventID = context.getResources().getIntArray(R.array.event_ID);
        String[] eventTitles = context.getResources().getStringArray(R.array.event_titles);
        String[] eventInfo = context.getResources().getStringArray(R.array.event_info);
        TypedArray eventImageResources = context.getResources().obtainTypedArray(R.array.event_images);

        Event event = new Event();
        if (event != null) {
            for (int i = 0; i < 3; i++) {
                eventList.add(new Event(eventID[i], eventTitles[i], eventInfo[i],
                        eventImageResources.getResourceId(i, 0)));
            }
        }
        EventStore.setEventList(eventList);
    }

}
