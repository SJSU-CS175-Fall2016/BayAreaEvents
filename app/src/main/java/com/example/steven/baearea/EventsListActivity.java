package com.example.steven.baearea;

import android.app.Activity;
import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joan on 12/5/16.
 */
public class EventsListActivity extends Activity {

    private ArrayList<EventData> events;
    //private ListView eventsList;
    private AdapterView eventsList;
    private Spinner locationSpinner;
    private LayoutInflater layoutInflator;
    private Button searchButton;
    private EventbriteIconTask imgFetcher;
    private InputMethodManager inMgr;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.eventsactivity);

       // this.inMgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        this.locationSpinner = (Spinner) findViewById(R.id.location_spinner);
        this.searchButton= (Button) findViewById(R.id.search_button);
        this.imgFetcher =new EventbriteIconTask(); // add (this)
        this.layoutInflator =LayoutInflater.from(this);
        this.eventsList = (ListView) findViewById(R.id.event_list);


        this.searchButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //inMgr.hideSoftInputFromWindow(searchButton.getWindowToken(), 0);
                EventbriteWebAPITask evtTask = new EventbriteWebAPITask(EventsListActivity.this);
                try {
                    //create a task that finds events for specified locations
                    TextView txtView  =  (TextView)locationSpinner.getSelectedView();
                    String metroTxt = txtView.getText().toString();
                    evtTask.execute(metroTxt);
                }
                catch (Exception e)
                {
                    evtTask.cancel(true);
                    alert (getResources().getString(R.string.no_events));
                }

            }
        });

        final Object[] data = (Object[]) getLastNonConfigurationInstance();

        if(data != null) {
            this.events = (ArrayList<EventData>) data[0];
            this.imgFetcher = (EventbriteIconTask)data[1];
            eventsList.setAdapter(new EventListAdapter(this,this.imgFetcher,this.layoutInflator, this.events));
        }

        // pre populates the spinner with cities
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Location, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);

        }


    public static class MyViewHolder {
        public TextView eventName, eventDate;
        public Button searchButton;
        public ImageView icon;
        public EventData event;
    }


    public void alert (String msg)
    {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }



    public void setEvents(ArrayList<EventData> events) {
        System.out.println("6");
        //saves the event array as local obj
        this.events = events;
        this.eventsList.setAdapter(new EventListAdapter (this,this.imgFetcher,this.layoutInflator, this.events));

        System.out.println(eventsList);
        System.out.println("7");
    }



    }



