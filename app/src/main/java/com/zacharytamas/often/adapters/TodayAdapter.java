package com.zacharytamas.often.adapters;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.github.pavlospt.CircleView;
import com.zacharytamas.often.R;
import com.zacharytamas.often.models.Habit;
import com.zacharytamas.often.utils.Dates;

import org.joda.time.DateTime;

import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

/**
 * Created by zjones on 12/11/14.
 */
public class TodayAdapter extends RealmBaseAdapter<Habit> implements ListAdapter {

    private static final int LAYOUT = R.layout.item_habit;

    private static class ViewHolder {
        TextView habitTitle;
        TextView lastCompletedTextView;
        CircleView circleView;
    }

    public TodayAdapter(Context context, RealmResults<Habit> results, boolean automaticUpdate) {
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
            viewHolder.circleView = (CircleView) view.findViewById(R.id.circleView);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Habit habit = realmResults.get(i);
        viewHolder.habitTitle.setText(habit.getTitle());

        String convertedDate = "";

        if (habit.getLastCompletedAt() != null) {
            convertedDate = view.getContext().getString(R.string.item_habit_last_completed) +
                    DateUtils.getRelativeTimeSpanString(habit.getLastCompletedAt().getTime(),
                            new DateTime().getMillis(),
                            DateUtils.SECOND_IN_MILLIS).toString();
        }

        viewHolder.lastCompletedTextView.setText(convertedDate);

        //
        // Circle
        //

        Integer circleColor;

        if (habit.isRequired()) {
            if (Dates.isOverdue(habit)) {
                circleColor = context.getResources().getColor(R.color.red);
            } else {
                circleColor = context.getResources().getColor(R.color.green);
            }
            viewHolder.circleView.setTitleText(Integer.toString(habit.getStreakValue()));
        } else {
            circleColor = context.getResources().getColor(R.color.grey);
        }

        viewHolder.circleView.setFillColor(circleColor);


        return view;
    }
}
