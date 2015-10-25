package com.mycompany.capstone;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by sjayaram on 10/24/2015.
 */
public class ConceptsAdapter  extends ArrayAdapter<Concepts> {

    private static class ViewHolder {
        TextView tag;
        TextView weight;
    }


    public ConceptsAdapter(Context context, List<Concepts> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Concepts photo  = getItem(position);
        final ViewHolder viewHolder;

        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_concept, parent, false);
            viewHolder.tag = (TextView) convertView.findViewById(R.id.tvTag);
            viewHolder.weight = (TextView) convertView.findViewById(R.id.tvWeight);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //set Details
        viewHolder.tag.setText(photo.tag);
        viewHolder.weight.setText(photo.weight);
        return convertView;
    }

}
