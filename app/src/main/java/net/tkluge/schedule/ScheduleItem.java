package net.tkluge.schedule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kluget on 9/2/2015.
 */
public class ScheduleItem {

    public String name = "Name",
                  location = "Location",
                  time_start = "0:00 AM",
                  time_end = "",
                  detail = "",
                  time_format = "",
                  time_name = "";
    public static ScheduleItem fromJSON(JSONObject jsonObject) {
        ScheduleItem si = new ScheduleItem();
        try {
            si.name = jsonObject.getString("name");
            si.detail = jsonObject.getString("detail");
            si.location = jsonObject.getString("location");
            si.time_format = jsonObject.getString("time_format");
            si.time_start = jsonObject.getString("time_start");
            si.time_end = jsonObject.getString("time_end");
            si.time_name = jsonObject.getString("time_name");
        } catch (JSONException e) {

        }
        if (si.time_end.equals("") || si.time_end == null) {
            int end = Integer.parseInt(si.time_start) + 1;
            if (end == 13) end = 1;
            si.time_end = Integer.toString(end);
        }
        return si;
    }
    //from json array, returned from server
    public static ArrayList<ScheduleItem> fromJSON(JSONArray arr) {
        ArrayList<ScheduleItem> events = new ArrayList<ScheduleItem>();
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj;
            try {
                obj = arr.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            ScheduleItem file = ScheduleItem.fromJSON(obj);
            if (file != null) {
                events.add(file);
            }
        }
        return events;
    }
    public static ArrayList<ScheduleItem> schedule2Day(JSONArray arr, int day) {
        ArrayList<ScheduleItem> events = null;
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj;
            JSONArray arr_sub;
            try {
                obj = arr.getJSONObject(i);
                if (obj.getString("day_number").equals(Integer.toString(day))) {
                    arr_sub = obj.getJSONArray("events");
                    events = ScheduleItem.fromJSON(arr_sub);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (events == null) {

        }
        return events;
    }
}
