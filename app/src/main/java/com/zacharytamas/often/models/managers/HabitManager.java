package com.zacharytamas.often.models.managers;

import android.content.Context;

import io.realm.Realm;

/**
 * Created by zacharytamas on 12/11/14.
 */
public class HabitManager {

    private Context mContext;
    private Realm mRealm;

    public HabitManager(Context context) {
        mContext = context;
        mRealm = Realm.getInstance(mContext);
    }

}
