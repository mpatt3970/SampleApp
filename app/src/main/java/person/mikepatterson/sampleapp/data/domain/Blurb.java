package person.mikepatterson.sampleapp.data.domain;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import person.mikepatterson.sampleapp.data.pojo.BlurbPojo;

// This domain model is more than just the POJO and can include more business logic than the POJO or ViewModel
public class Blurb extends RealmObject {

    @PrimaryKey
    private int id;
    private String title;
    private String description;
    private String imageUrl;

    public static void copyPojo(Realm realm, BlurbPojo pojo) {
        Blurb blurb = realm.createObject(Blurb.class);
        blurb.setId(pojo.id);
        blurb.setTitle(pojo.title);
        blurb.setDescription(pojo.description);
        blurb.setImageUrl(pojo.imageUrl);
        realm.copyToRealm(blurb);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
