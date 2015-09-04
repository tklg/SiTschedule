package net.tkluge.schedule;

/**
 * Created by kluget on 9/2/2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ScheduleItemAdapter extends ArrayAdapter<ScheduleItem> {

    private static class ViewHolder {
        TextView name;
        TextView location;
        Button clickmenu;
        TextView time_start;
        TextView time_end;
    }
    private static Context context;

    public ScheduleItemAdapter(Context context, ArrayList<ScheduleItem> days) {
        super(context, 0, days);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ScheduleItem file = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_timeslot, parent, false);

            viewHolder.name = (TextView) convertView.findViewById(R.id.event_name);
            viewHolder.location = (TextView) convertView.findViewById(R.id.event_location);
            viewHolder.time_start = (TextView) convertView.findViewById(R.id.event_time_start);
            viewHolder.time_end = (TextView) convertView.findViewById(R.id.event_time_end);
            //viewHolder.clickmenu = (Button) convertView.findViewById(R.id.btn_edit);
            //viewHolder.icon = (TextView) convertView.findViewById(R.id.file_icon);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String time_start = null,
                time_end = null;
        try {
            Date date = null;
            if (file.time_start.equals("12") && file.time_name.equalsIgnoreCase("pm")) {
                time_start = "Noon";
            } else if (file.time_start.equals("12") && file.time_name.equalsIgnoreCase("am")) {
                time_start = "Midnight";
            } else {
                date = new SimpleDateFormat("HH").parse(file.time_start);
                time_start = new SimpleDateFormat("H:mm").format(date);
                time_start += " " + file.time_name.toUpperCase();
            }
            if (file.time_end.equals("12") && file.time_name.equalsIgnoreCase("pm")) {
                time_end = "Noon";
            } else if (file.time_start.equals("12") && file.time_name.equalsIgnoreCase("am")) {
                time_end = "Midnight";
            } else {
                date = new SimpleDateFormat("HH").parse(file.time_end);
                time_end = new SimpleDateFormat("H:mm").format(date);
            }
        } catch (Exception e) {

        }
        time_end = "until " + time_end;
        //fill template
        viewHolder.name.setText(file.name);
        viewHolder.location.setText(file.location);
        viewHolder.time_start.setText(time_start);
        viewHolder.time_end.setText(time_end);
        //viewHolder.clickmenu.setTypeface(Typefaces.get(context, "fontawesome-webfont.ttf"));
        /*int stringID = context.getResources().getIdentifier("icon_" + file.getType(), "string", context.getApplicationInfo().packageName);
        if (stringID == 0) {
            throw new IllegalArgumentException("No resource string found with name " + "icon_" + file.getType());
        } else {
            viewHolder.icon.setText(context.getString(stringID));
        }
        viewHolder.icon.setTypeface(Typefaces.get(context, "fontawesome-webfont.ttf"));*/

        return convertView;
    }
}
