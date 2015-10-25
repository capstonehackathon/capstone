package com.mycompany.capstone;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ekucukog on 10/24/2015.
 */
public class MeetupEventsAdapter extends ArrayAdapter<MeetupEvent> {

    // View lookup cache
    private static class ViewHolder {
        TextView tvName;
        TextView tvDescription;
    }

    public MeetupEventsAdapter(Context context, List<MeetupEvent> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MeetupEvent event = getItem(position);

        ViewHolder viewHolder;
        if(convertView == null){//not recycled view
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_event, parent, false);

            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);

            viewHolder.tvName.setText(event.name);
            viewHolder.tvDescription.setText(Html.fromHtml(event.description));

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }
}

