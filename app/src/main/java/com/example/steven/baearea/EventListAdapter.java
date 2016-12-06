package com.example.steven.baearea;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Joan on 12/5/16.
 */
public class EventListAdapter extends BaseAdapter implements View.OnClickListener {
    private static final String debugTag = "EventDataAdapter";
    private EventsListActivity activity;
    private EventbriteIconTask imgFetcher;
    private LayoutInflater layoutInflater;
    private ArrayList<EventData> events;



    public EventListAdapter(EventsListActivity eventsListActivity, EventbriteIconTask imgFetcher,
                            ArrayList<EventData> events, LayoutInflater layoutInflator) {

        this.activity = eventsListActivity;
        this.imgFetcher=imgFetcher;
        this.layoutInflater = layoutInflator;
        this.events=events;

    }

    @Override
    public int getCount() {
        return 0;
    }


    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      // when the list shows, the framework call getView
        EventsListActivity.MyViewHolder holder;
        if (convertView == null) {
            //switch view to eventrow
            convertView = layoutInflater.inflate (R.layout.eventrow, parent, false);
            holder = new EventsListActivity.MyViewHolder();
            holder.eventName = (TextView) convertView.findViewById(R.id.event_name);
            holder.eventDate = (TextView) convertView.findViewById(R.id.event_date);
            holder.icon = (ImageView) convertView.findViewById(R.id.event_icon);
            holder.searchButton = (Button) convertView.findViewById(R.id.event_button);
            holder.searchButton.setTag(holder);
            convertView.setTag(holder);
        }
        else {
            holder = (EventsListActivity.MyViewHolder) convertView.getTag();
        }

        convertView.setOnClickListener(this);

        EventData event = events.get(position);
        holder.event = event;
        holder.eventName.setText(event.getEventName());
        holder.eventDate.setText(event.getEventURL());
        holder.searchButton.setOnClickListener(this);
        if(event.getEventImg() != null) {
            holder.icon.setTag(event.getEventImg());
            Drawable dr = imgFetcher.loadImage(this, holder.icon);
            if(dr != null) {
                holder.icon.setImageDrawable(dr);
            }
        } else {
            holder.icon.setImageResource(R.drawable.icon1);
        }

        return convertView;

    }

    @Override
    public void onClick(View v) {
        EventsListActivity.MyViewHolder holder = (EventsListActivity.MyViewHolder) v.getTag();
        if (v instanceof Button) {

            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse(holder.event.getEventURL()));
            this.activity.startActivity(intent);

        } else if (v instanceof View) {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse(holder.event.getEventDate()));
            this.activity.startActivity(intent);
        }
        Log.d(debugTag,"OnClick pressed.");

    }


}



