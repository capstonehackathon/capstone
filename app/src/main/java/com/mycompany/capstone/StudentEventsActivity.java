package com.mycompany.capstone;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

public class StudentEventsActivity extends ActionBarActivity {

    private String[] keywords = {"java, programming, technology"};
    private ArrayList<MeetupEvent> events;
    private MeetupEventsAdapter aEvents;
    private ListView lvEvents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_events);

        lvEvents = (ListView) findViewById(R.id.lvEvents);
        events = new ArrayList<>();
        aEvents = new MeetupEventsAdapter(this, events);
        lvEvents.setAdapter(aEvents);

        populateEvents();
    }

    private void populateEvents(){

        StringBuilder sb = new StringBuilder();
        for(String keyword: keywords){
            sb.append(keyword);
        }
        String concepts = sb.toString();
        Toast.makeText(this, "Concepts are: " + concepts, Toast.LENGTH_SHORT).show();
        AsyncHttpClient client = new AsyncHttpClient();

        String keys = "hiking";
        String myKey = "197f3d7a6138314847473f6d7b27668";
        String baseUrl = "https://api.meetup.com";
        String middle = "/2/open_events?zip=94403&radius=smart&text=" + keys;
        String tail = "&page=7&key="+myKey+"&sign=true";
        String url = baseUrl + middle + tail;

        client.get(url, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                Log.i("DEBUG", response.toString());
                JSONArray eventsJson = null;
                try {
                    //???????????
                    eventsJson = response.getJSONArray("results");
                    Log.d("DEBUG", eventsJson.toString());

                    events.addAll(MeetupEvent.fromJsonArray(eventsJson));
                    aEvents.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.i("DEBUG", events.toString());
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Log.d("DEBUG", responseString);
                Toast.makeText(getApplicationContext(), "Connection failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_events, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
