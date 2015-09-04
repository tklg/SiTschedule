package net.tkluge.schedule;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by kluget on 9/2/2015.
 */
public class ScheduleFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private ListView listView;
    private ScheduleItemAdapter adapter;
    private Context context;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ScheduleFragment newInstance(int sectionNumber) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ScheduleFragment() {
        //ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        this.setArguments(args);
        //return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = container.getContext();
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);

        JSONArray schedule = null;

        String filepath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/schedule.json";
        try {
            File f = new File(filepath);
            if (f.exists()) {
                InputStream is = new FileInputStream(filepath);
                String jsonTxt = IOUtils.toString(is);
                schedule = new JSONArray(jsonTxt);
            } else {
                F.nl("COULD NOT FIND FILE: " + filepath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        int day_num = c.get(Calendar.DAY_OF_WEEK);

        ArrayList<ScheduleItem> day = ScheduleItem.schedule2Day(schedule, day_num);
        ScheduleItemAdapter adapter = new ScheduleItemAdapter(context, day);

        ListView listView = (ListView) rootView.findViewById(R.id.calendar_content);
        listView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
