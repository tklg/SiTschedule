package net.tkluge.schedule;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

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

    private NavigationDrawerFragment mNavigationDrawerFragment;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);

        getActivity().getActionBar().setElevation(0);

        Calendar cal = Calendar.getInstance();

        DateFormat df = new SimpleDateFormat("EEEE, LLL. d");
        String day_name = df.format(cal.getTime());
        TextView calendar_clock_date = (TextView) rootView.findViewById(R.id.calendar_clock_date);
        calendar_clock_date.setText(day_name);

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

        int day_num = cal.get(Calendar.DAY_OF_WEEK);

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
