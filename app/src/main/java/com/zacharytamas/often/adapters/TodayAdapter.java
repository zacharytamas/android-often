package com.zacharytamas.often.adapters;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.zacharytamas.often.R;
import com.zacharytamas.often.models.Habit;
import com.zacharytamas.often.models.HabitOccurrence;

import java.util.Date;
import java.util.List;

import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

/**
 * Created by zjones on 12/11/14.
 */
public class TodayAdapter extends RealmBaseAdapter<HabitOccurrence> implements ListAdapter {

    private static final int LAYOUT = R.layout.item_habit;

    private static class ViewHolder {
        TextView habitTitle;
        TextView lastCompletedTextView;
    }

    public TodayAdapter(Context context, RealmResults<HabitOccurrence> results, boolean automaticUpdate) {
        super(context, results, automaticUpdate);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            view = inflater.inflate(LAYOUT, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.habitTitle = (TextView) view.findViewById(R.id.habitTitle);
            viewHolder.lastCompletedTextView = (TextView) view.findViewById(R.id.lastCompletedTextView);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        HabitOccurrence occurrence = realmResults.get(i);
        Habit habit = occurrence.getHabit();
        viewHolder.habitTitle.setText(habit.getTitle());

        String convertedDate = "";

        if (habit.getLastCompletedAt() != null) {
            convertedDate = view.getContext().getString(R.string.item_habit_last_completed) +
                    DateUtils.getRelativeTimeSpanString(habit.getLastCompletedAt().getTime(),
                    new Date().getTime(),
                    DateUtils.SECOND_IN_MILLIS).toString().toLowerCase();
        }

        viewHolder.lastCompletedTextView.setText(convertedDate);

        return view;
    }
}
