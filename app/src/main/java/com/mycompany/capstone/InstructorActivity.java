package com.mycompany.capstone;

import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class InstructorActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);

        Button btUpload = (Button) findViewById(R.id.btUpload);
        btUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText tvUrl = (EditText)findViewById(R.id.tvUrl);

                AsyncHttpClient client = new AsyncHttpClient();
                StringBuilder url = new StringBuilder("http://gateway-a.watsonplatform.net/calls/url/URLGetRankedConcepts?outputMode=json&url=" +
                         "http://www.columbia.edu/cu/arthistory/courses/Multiple-Modernities/syllabus.html&apikey=ec77ff401e0789cab7c1108c3b6b8b09f9b826d5");

                client.get(url.toString(), new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response)
                    {
                        Log.d("Debug ", response.toString());
                        ArrayList<Concepts> list = new ArrayList<Concepts>();

                        try {
                            JSONArray imageResultsJson = response.getJSONArray("concepts");

                            for(int i = 0; i < imageResultsJson.length(); i++) {
                                JSONObject photoJson = imageResultsJson.getJSONObject(i);
                                String text = photoJson.getString("text");
                                String relevance = photoJson.getString("relevance");
                                list.add(new Concepts(text, relevance));
                            }

                            Intent i = new Intent(InstructorActivity.this, ConceptsActivity.class);
                            i.putExtra("concepts", list);
                            startActivity(i);
                            Log.i("DEBUG" , list.toString());

                        } catch (JSONException ex) {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                            ex.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Log.d("DEBUG", "Fetch error");
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }

                });
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_instructor, menu);
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
