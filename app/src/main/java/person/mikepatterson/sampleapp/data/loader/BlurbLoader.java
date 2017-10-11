package person.mikepatterson.sampleapp.data.loader;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import person.mikepatterson.sampleapp.data.domain.Blurb;

public class BlurbLoader extends BaseLoader<List<Blurb>> {

    @Override
    public void addRealmListener() {
        final Realm realm = Realm.getDefaultInstance();
        realm.addChangeListener(new RealmChangeListener() {
            @Override
            public void onChange() {
                RealmResults<Blurb> realmResults = realm.where(Blurb.class).findAll();
                List<Blurb> blurbs = realm.copyFromRealm(realmResults);
                notifyChange(blurbs);
            }
        });
    }
}
