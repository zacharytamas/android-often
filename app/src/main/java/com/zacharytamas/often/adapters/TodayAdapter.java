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

import java.util.ArrayList;

import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

/**
 * Created by zjones on 12/11/14.
 */
public class TodayAdapter extends RealmBaseAdapter<Habit> implements ListAdapter {

    private static final int HABIT_LAYOUT = R.layout.item_habit;
    private static final int SECTION_HEADER_LAYOUT = R.layout.item_list_header;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_HABIT = 1;

    RealmResults<Habit> mDueHabits;
    RealmResults<Habit> mAvailableHabits;
    ArrayList<Row> mRows;

    private static class Row {

        int type;
        String title;
        Habit habit;

        private Row(int type, String title) {
            this.type = type;
            this.title = title;
        }

        private Row(int type, Habit habit) {
            this.type = type;
            this.habit = habit;
        }
    }

    private static class ViewHolder {
        TextView habitTitle;
        TextView lastCompletedTextView;
        CircleView circleView;
        TextView sectionHeader;
    }

    public TodayAdapter(Context context, RealmResults<Habit> dueHabits, RealmResults<Habit> availableHabits) {
        super(context, dueHabits, true);

        refill(dueHabits, availableHabits);
    }

    public void refill(RealmResults<Habit> dueHabits, RealmResults<Habit> availableHabits) {
        mDueHabits = dueHabits;
        mAvailableHabits = availableHabits;
        mRows = new ArrayList<>();

        // Build row list
        if (mDueHabits.size() > 0) {
            mRows.add(new Row(TYPE_HEADER, context.getString(R.string.list_header_due)));
            for (Habit habit : mDueHabits) {
                mRows.add(new Row(TYPE_HABIT, habit));
            }
        }

        if (mAvailableHabits.size() > 0) {
            mRows.add(new Row(TYPE_HEADER, context.getString(R.string.list_header_available)));
            for (Habit habit : mAvailableHabits) {
                mRows.add(new Row(TYPE_HABIT, habit));
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public boolean isEnabled(int position) {
        return mRows.get(position).type == TYPE_HABIT;
    }

    @Override
    public int getViewTypeCount() {
        // Headers and Habits
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return mRows.get(position).type;
    }

    @Override
    public int getCount() {
        return mRows.size();
    }

    @Override
    public Habit getItem(int i) {
        return mRows.get(i).habit;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        Row row = mRows.get(i);

        if (view == null) {
            viewHolder = new ViewHolder();

            switch (row.type) {
                case TYPE_HEADER:
                    view = inflater.inflate(SECTION_HEADER_LAYOUT, viewGroup, false);
                    viewHolder.sectionHeader = (TextView) view.findViewById(R.id.sectionTitleTextView);
                    break;
                case TYPE_HABIT:
                    view = inflater.inflate(HABIT_LAYOUT, viewGroup, false);
                    viewHolder.habitTitle = (TextView) view.findViewById(R.id.habitTitle);
                    viewHolder.lastCompletedTextView = (TextView) view.findViewById(R.id.lastCompletedTextView);
                    viewHolder.circleView = (CircleView) view.findViewById(R.id.circleView);
                    break;
            }

            if (view != null) {
                view.setTag(viewHolder);
            }
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if (row.type == TYPE_HABIT) {
            Habit habit = mRows.get(i).habit;

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
        } else if (row.type == TYPE_HEADER) {
            viewHolder.sectionHeader.setText(row.title);
        }

        return view;
    }
}
