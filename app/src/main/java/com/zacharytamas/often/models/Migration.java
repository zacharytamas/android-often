package com.zacharytamas.often.models;

import io.realm.Realm;
import io.realm.RealmMigration;

/**
 * Created by zacharytamas on 12/11/14.
 */
public class Migration implements RealmMigration {

    @Override
    public long execute(Realm realm, long version) {
        return 0;
    }

}
