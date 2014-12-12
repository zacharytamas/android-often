package com.zacharytamas.often.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.zacharytamas.often.adapters.TodayAdapter;
import com.zacharytamas.often.models.Habit;
import com.zacharytamas.often.models.HabitOccurrence;
import com.zacharytamas.often.models.managers.HabitManager;
import com.zacharytamas.often.models.managers.HabitOccurrenceManager;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A fragment representing a list of Items.
 */
public class TodayListFragment extends ListFragment {

    private TodayAdapter mAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // TODO: Rename and change types of parameters
    public static TodayListFragment newInstance(String param1, String param2) {
        TodayListFragment fragment = new TodayListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TodayListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        HabitManager manager = new HabitManager(getActivity());

        RealmResults<Habit> results = manager.getAvailableHabits();

        mAdapter = new TodayAdapter(getActivity(), results, true);

        setListAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

}
