package com.mycompany.capstone;

/**
 * Created by ekucukog on 10/24/2015.
 */

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MeetupEvent {
    public String name;
    public String description;

    public static MeetupEvent fromJson(JSONObject jsonObject){
        MeetupEvent event = new MeetupEvent();
        //Extract the values from json, store them
        try {

            //do these correctly
            event.name = jsonObject.getString("name");
            event.description = jsonObject.getString("description");
            Log.d("DEBUG", "name: " + event.name + " description: " + event.description);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //return tweet object
        return event;
    }

    public static ArrayList<MeetupEvent> fromJsonArray(JSONArray jsonArray){

        ArrayList<MeetupEvent> events = new ArrayList<>();

        for(int i=0; i<jsonArray.length(); i++){
            try {
                JSONObject eventJson = jsonArray.getJSONObject(i);
                MeetupEvent event = MeetupEvent.fromJson(eventJson);
                if(event!=null){
                    events.add(event);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }

        return events;
    }
}

