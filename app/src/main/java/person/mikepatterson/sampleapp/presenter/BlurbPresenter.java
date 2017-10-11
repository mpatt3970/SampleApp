package person.mikepatterson.sampleapp.presenter;

import android.os.Handler;
import android.util.Log;

import java.util.List;

import person.mikepatterson.sampleapp.SampleApp;
import person.mikepatterson.sampleapp.data.api.ApiClient;
import person.mikepatterson.sampleapp.data.domain.Blurb;
import person.mikepatterson.sampleapp.data.loader.BaseLoader;
import person.mikepatterson.sampleapp.data.loader.BlurbLoader;
import person.mikepatterson.sampleapp.data.producer.BlurbProducer;

public class BlurbPresenter extends BasePresenter implements BaseLoader.LoaderListener<List<Blurb>> {

    private static final String TAG = SampleApp.CLASS_TAG(BlurbPresenter.class);

    private PresenterListener listener;
    private BlurbProducer producer;
    private BlurbLoader loader;

    public BlurbPresenter(SampleApp app) {
        ApiClient apiClient = app.getApiClient();
        Handler mainThreadHandler = app.getMainThreadHandler();
        producer = new BlurbProducer(apiClient, mainThreadHandler);
        loader = new BlurbLoader();
    }

    @Override
    public void register(PresenterListener listener) {
        this.listener = listener;
        loader.register(this);
        producer.produce();
    }

    @Override
    public void unregister() {
        listener = null;
        loader.unregister();
        producer.cancel();
    }

    @Override
    public void onChange(List<Blurb> data) {
        Log.d(TAG, "onChange");
    }
}
