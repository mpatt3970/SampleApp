package person.mikepatterson.sampleapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import person.mikepatterson.sampleapp.R;
import person.mikepatterson.sampleapp.SampleApp;
import person.mikepatterson.sampleapp.presenter.BasePresenter;
import person.mikepatterson.sampleapp.presenter.BlurbPresenter;
import person.mikepatterson.sampleapp.viewmodel.BaseViewModel;

public class MainActivity extends AppCompatActivity implements BasePresenter.PresenterListener {
    private static final String TAG = SampleApp.CLASS_TAG(MainActivity.class);

    private BlurbPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new BlurbPresenter((SampleApp) getApplication());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        presenter.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        presenter.unregister();
    }

    @Override
    public void onUpdateUi(BaseViewModel viewModel) {

    }
}
