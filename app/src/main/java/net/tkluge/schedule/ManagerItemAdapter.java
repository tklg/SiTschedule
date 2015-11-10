package net.tkluge.schedule;

/**
 * Created by kluget on 9/2/2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ManagerItemAdapter extends ArrayAdapter<ManagerItem> {

    private static class ViewHolder {
        TextView name;
    }
    private static Context context;
    private static ArrayList<ManagerItem> a;

    public ManagerItemAdapter(Context context, ArrayList<ManagerItem> schedules) {
        super(context, 0, schedules);
        a = schedules;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ManagerItem file = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_timeslot, parent, false);

            viewHolder.name = (TextView) convertView.findViewById(R.id.event_name);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //fill template
        viewHolder.name.setText(file.name);

        return convertView;
    }
}
