package person.mikepatterson.sampleapp.data.producer;

import android.os.Handler;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import person.mikepatterson.sampleapp.data.api.ApiClient;

public abstract class BaseProducer {

    protected ApiClient apiClient;
    private Handler mainThreadHandler;
    protected boolean canceled;

    public interface OnResultListener {
        void onSuccess();
        void onFailure();
    }

    public BaseProducer(ApiClient apiClient, Handler mainThreadHandler) {
        this.apiClient = apiClient;
        this.mainThreadHandler = mainThreadHandler;
    }

    public void produce() {
        produce(null);
    }

    @CallSuper
    public void produce(@Nullable OnResultListener listener) {
        canceled = false;
    }

    public void cancel() {
        canceled = true;
    }

    protected void notifySuccess(@Nullable final OnResultListener listener) {
        if (listener == null || canceled) {
            return;
        }
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                listener.onSuccess();
            }
        });
    }

    protected void notifyFailure(@Nullable final OnResultListener listener) {
        if (listener == null || canceled) {
            return;
        }
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                listener.onFailure();
            }
        });
    }
}
