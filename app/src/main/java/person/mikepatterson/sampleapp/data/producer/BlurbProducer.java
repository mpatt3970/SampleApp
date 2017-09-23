package person.mikepatterson.sampleapp.data.producer;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import person.mikepatterson.sampleapp.SampleApp;
import person.mikepatterson.sampleapp.data.api.ApiClient;
import person.mikepatterson.sampleapp.data.pojo.Blurb;
import person.mikepatterson.sampleapp.data.pojo.BlurbResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlurbProducer extends BaseProducer {
    private static final String TAG = SampleApp.CLASS_TAG(BlurbProducer.class);

    public BlurbProducer(ApiClient apiClient, Handler mainThreadHandler) {
        super(apiClient, mainThreadHandler);
    }

    @Override
    public void produce(@Nullable final OnResultListener listener) {
        super.produce(listener);
        apiClient.getBlurbs(new Callback<BlurbResponse>() {
            @Override
            public void onResponse(@NonNull Call<BlurbResponse> call, @NonNull Response<BlurbResponse> response) {
                Log.d(TAG, "onResponse");
                BlurbResponse body = response.body();
                if (body == null || body.blurbs == null || body.blurbs.size() == 0) {
                    Log.e(TAG, "emptyResponse");
                    return;
                }
                for (Blurb blurb : body.blurbs) {
                    Log.d(TAG, "Blurb: " + blurb.id + " : " + blurb.title);
                }
                notifySuccess(listener);
            }

            @Override
            public void onFailure(@NonNull Call<BlurbResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "onError", t);
                notifyFailure(listener);
            }
        });
    }


}
