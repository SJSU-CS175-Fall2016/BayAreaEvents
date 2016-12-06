package com.example.steven.baearea;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joan on 12/5/16.
 */
public class EventbriteWebAPITask extends AsyncTask<String,Integer,String> {

    private ProgressDialog progDialog;
    private Context context;
    private EventsListActivity activity;
    private static final String debugTag = "LastFMWebAPITask";


    public EventbriteWebAPITask(EventsListActivity activity) {
        super();
        this.activity = activity;
        this.context = this.activity.getApplicationContext();

    }// indicate to the UI before the task starts running

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progDialog = ProgressDialog.show(this.activity, "Search", this.context.getResources().getString(R.string.searching_for_events), true, false);
    }


    @Override
    // will be called from a worker thread
    protected String doInBackground(String... searchString) {
        try {
            Log.d(debugTag, "Background:" + Thread.currentThread().getName());
            String result = EventbriteHelper.downloadFromServer(searchString);
            return result;
        } catch (Exception e) {
            return new String();
        }

    }

    protected void onPostExecute(String result) {
        ArrayList<EventData> eventdata = new ArrayList<EventData>();

        progDialog.dismiss();
        if (result.length() == 0) {
            System.out.println("unable to find an events, Please try again later");
            return;
        }

        try {
            {
                JSONObject respObj = new JSONObject(result);
                JSONObject EventsObj = respObj.getJSONObject("event");
                JSONArray events = EventsObj.getJSONArray("events");

                for (int i=0; i<events.length();i++) {
                    JSONObject event = events.getJSONObject(i);
                    String eventCategory = event.getString("Category");
                    String eventName = event.getString("name");
                    String eventURL = event.getString("url");
                    String eventCap = event.getString("capacity");
                    String eventImg = event.getString("image");

                    eventdata.add(new EventData(eventCategory,eventName,eventURL, eventCap, eventImg));

                }

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.activity.setEvents(eventdata);
    }
}
