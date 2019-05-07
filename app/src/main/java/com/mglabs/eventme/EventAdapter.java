package com.mglabs.eventme;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mglabs.eventme.model.Event;
import com.mglabs.eventme.model.EventStore;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    //Member variables
    EventStore store;
    private LayoutInflater mInflater;
    Event mCurrentEvent;
    private Context mContext;

    public static String EVENT_ID_EXTRA = " ";
    public static String EVENT_IMAGE_EXTRA = "";

    //Adapter constructor
    public EventAdapter(Context context, EventStore store) {
        mInflater = LayoutInflater.from(context);
        this.store = store;
        this.mContext = context;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, int position) {
        if (store.getEventList() != null) {
            Event mCurrentEvent = store.getEventList().get(position);
            eventViewHolder.bindTo(mCurrentEvent);
        }
    }

    @Override
    public int getItemCount() {
        if (store.getEventList() != null)
            return store.getEventList().size();
        else
            return 0;
    }

    /**
     * ViewHolder class that represents each row of data in the RecyclerView
     */
    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView eventTitle;
        final ImageView eventImage;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file
         */
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            //Initialize the views
            eventTitle = itemView.findViewById(R.id.title_textView);
            eventImage = itemView.findViewById(R.id.event_image);

            //Set the onClickListener on the entire view
            itemView.setOnClickListener(this);
        }

        void bindTo(Event currentEvent) {
            //Populate the textviews with data and images with Glide
            eventTitle.setText(currentEvent.getTitle());
            //eventInfo.setText(currentEvent.getInfo());
            Glide.with(mContext).load(currentEvent.getImageResource()).into(eventImage);
        }

        @Override
        public void onClick(View view) {
            if (store.getEventList() != null) {
                int position = getAdapterPosition();
                Event event = store.getEventList().get(position);
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(EVENT_ID_EXTRA, event.getEventID());
                intent.putExtra(EVENT_IMAGE_EXTRA, event.getImageResource());
                mContext.startActivity(intent);

                Log.i("ID_INFO", "onClick:" + event.getInfo());

            } else {
                Toast.makeText(mContext, "EVENT NOT FOUND", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
