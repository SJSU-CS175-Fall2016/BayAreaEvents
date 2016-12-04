package com.example.steven.baearea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class EventListPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page2);

        Button checkAvail = (Button) findViewById(R.id.checkAvail);
        checkAvail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Clicks", "loading ...");
                Intent i = new Intent(EventListPage.this, EventPage.class);
                startActivity(i);

            }
        });



    }

}
