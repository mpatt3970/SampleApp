package person.mikepatterson.sampleapp.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import person.mikepatterson.sampleapp.R;
import person.mikepatterson.sampleapp.SampleApp;
import person.mikepatterson.sampleapp.data.api.ApiClient;
import person.mikepatterson.sampleapp.data.producer.BlurbProducer;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = SampleApp.CLASS_TAG(MainActivity.class);

    private ApiClient apiClient;
    private Handler mainThreadHandler;
    private BlurbProducer producer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SampleApp app = (SampleApp) getApplication();
        apiClient = app.getApiClient();
        mainThreadHandler = app.getMainThreadHandler();
        producer = new BlurbProducer(apiClient, mainThreadHandler);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        producer.produce();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        producer.cancel();
    }
}
