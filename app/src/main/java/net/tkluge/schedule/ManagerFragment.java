package net.tkluge.schedule;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by kluget on 9/2/2015.
 */
public class ManagerFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private Context context;

   /* public static ManagerFragment newInstance(int sectionNumber) {
        ManagerFragment fragment = new ManagerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }*/
    public ManagerFragment() {
        //ManagerFragment fragment = new ManagerFragment();
        //return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_manager, container, false);
        context = container.getContext();
        getActivity().getActionBar().setElevation(7);

        ArrayList<ManagerItem> schedules = ManagerItem.schedulesToList();
        ManagerItemAdapter adapter = new ManagerItemAdapter(context, schedules);

        ListView listView = (ListView) rootView.findViewById(R.id.manager_list);
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
