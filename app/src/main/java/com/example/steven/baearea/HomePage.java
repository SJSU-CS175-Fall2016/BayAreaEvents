

package com.example.steven.baearea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class HomePage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        // Location button
        Button browseButton = (Button) findViewById(R.id.browseButton);
        browseButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // when  browse button is clicked
                Log.i("Clicks", "loading ...");
                Intent i = new Intent(HomePage.this, EventListPage.class);
                startActivity(i);



            }
        });

        Button searchButton = (Button) findViewById (R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //when search button is clicked
            }
        });

        // pre populated location

        Spinner locationSpinner = (Spinner) findViewById (R.id.locationspinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Location, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);

    }


}


