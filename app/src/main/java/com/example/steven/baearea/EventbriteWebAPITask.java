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
    private static final String debugTag = "EventbriteWebAPITask";


    public EventbriteWebAPITask(EventsListActivity activity) {
        super();
        this.activity = activity;
        this.context = this.activity.getApplicationContext();

    }// indicate to the UI before the task starts running

    @Override
    // search Dialog
    protected void onPreExecute() {
        super.onPreExecute();
        progDialog = ProgressDialog.show(this.activity, "Search", this.context.getResources().getString(R.string.searching_for_events), true, false);
    }


    @Override
    // will be called from a worker thread
    protected String doInBackground(String... params) {
        try {
            Log.d(debugTag, "Background:" + Thread.currentThread().getName());
            String result = EventbriteHelper.downloadFromServer(params);
            return result;
        } catch (Exception e) {
            return new String();
        }

    }

    protected void onPostExecute(String result) {
        ArrayList<EventData> eventdata = new ArrayList<EventData>();

        //get the dialog off the screen
        progDialog.dismiss();
        if (result.length() == 0) {
            //if there are no events to display
//            System.out.println("unable to find an events, Please try again later");
            this.activity.alert("unable to find events, Please try again later");
            return;
        }

        try {
            {
                System.out.println("1");
                JSONObject respObj = new JSONObject(result);
                //JSONObject EventsObj = respObj.getJSONObject("events");
                JSONArray events = respObj.getJSONArray("events");

                System.out.println("2");
                for (int i=0; i<events.length();i++) {
                    JSONObject event = events.getJSONObject(i);
                    String eventName = event.getString("name");
                    String eventDescription = event.getString("description");
                    //String eventCategory = event.getString("category");
                    String eventURL = event.getString("url");
                    String eventCap = event.getString("capacity");
                    String eventImg = event.getString("logo");

                    System.out.println(eventName);
                    System.out.println(eventDescription);
                    System.out.println(eventURL);

                    // eventdata.add(new EventData(eventCategory,eventName,eventURL, eventCap, eventImg));
                    eventdata.add(new EventData(eventName,eventDescription,eventURL,eventCap, eventImg));

                    System.out.println("4");

                }

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("5");

        this.activity.setEvents(eventdata);
    }
}
