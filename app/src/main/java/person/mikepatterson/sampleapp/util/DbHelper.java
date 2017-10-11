package person.mikepatterson.sampleapp.util;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DbHelper {

    // Up this when the DB objects are modified
    private static final int SCHEMA_VERSION = 1;

    public static void init(Context context) {
        RealmConfiguration configuration = new RealmConfiguration.Builder(context)
                .schemaVersion(SCHEMA_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();
        // Can add migration strategies or modules(?)
        Realm.setDefaultConfiguration(configuration);
    }
}
