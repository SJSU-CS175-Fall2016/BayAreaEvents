package com.example.steven.baearea;

import android.util.Log;



import java.io.ByteArrayOutputStream;
import java.io.InputStream;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

/**
 * Created by Joan on 12/4/16.
 * Get the public API's from Eventbrite
 */
public class EventbriteHelper {
    // specific event will be replaced in the "id"
    //search event
    //private static final String EventURL ="https://www.eventbriteapi.com/v3/events/search/?q=q&token=EHX6AHAT3A5NVBCLVKSA";
    private static final String EventURL ="https://www.eventbriteapi.com/v3/events/search/?q";
    
   // private static final String DateURL = "https://www.eventbriteapi.com/v3/events/search/start_date/?5GWBURM7CJDIIMTMVV";

    private static final int HTTP_STATUS_OK = 200;
    private static byte[] buff = new byte[1024];
    private static final String logTag = "Eventbrite";



    public static class ApiException extends Exception {
        private static final long serialVersionUID = 1L;

        public ApiException (String msg)
        {
            super (msg);
        }

        public ApiException (String msg, Throwable thr)
        {
            super (msg, thr);
        }
    }

    protected static synchronized String downloadFromServer (String... searchString)
            throws ApiException
    {

        // get events with specific id

        String retval = null;
        String q= searchString[0];
        String token = "EHX6AHAT3A5NVBCLVKSA";


        String url = EventURL + "=" + q +"&token=" + token;
        Log.d(logTag,"Fetching " + url);
        // create an http client and a request object.
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        try {

            // execute the request
            HttpResponse response = client.execute(request);
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() != HTTP_STATUS_OK) {
                // handle error here
                throw new ApiException("Invalid response from last.fm" +
                        status.toString());
            }

            // process the content.
            HttpEntity entity = response.getEntity();
            InputStream ist = entity.getContent();
            ByteArrayOutputStream content = new ByteArrayOutputStream();

            int readCount = 0;
            while ((readCount = ist.read(buff)) != -1) {
                content.write(buff, 0, readCount);
            }
            retval = new String (content.toByteArray());

        } catch (Exception e) {
            throw new ApiException("Problem connecting to the server " +
                    e.getMessage(), e);
        }

        return retval;
    }
}
