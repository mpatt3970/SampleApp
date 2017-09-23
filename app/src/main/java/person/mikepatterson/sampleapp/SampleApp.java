package person.mikepatterson.sampleapp;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.util.concurrent.Executor;

import okhttp3.OkHttpClient;
import person.mikepatterson.sampleapp.data.api.ApiClient;
import person.mikepatterson.sampleapp.data.api.ApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// This is the class that drives your whole application, declared in the manifest 'android:name=".SampleApp"'
public class SampleApp extends Application {

    public static final String APP_TAG = "UNIQUE_SAMPLE_APP_TAG";
    public static String CLASS_TAG(Class clazz) {
        return String.format("%s-%s", APP_TAG, clazz.getSimpleName());
    }

    private static final String BASE_URL = "https://my-json-server.typicode.com/mpatt3970/SampleAppDataHoster/";

    private OkHttpClient okHttpClient;
    private Gson gson;
    private Retrofit retrofit;
    private ApiService apiService;
    private ApiClient apiClient;
    private Handler mainThreadHandler;

    // NOTE: this is a hacky way to do dependency injection. Dagger is a much better solution
    // but Dagger involves significant overhead to put up
    //
    // Dependency Injection is important so that for important shared items in the app, there is only ONE instance
    // OkHttpClient falls into the important shared item category
    public OkHttpClient getOkHttpClient() {
        // Lazy load the client, i.e. only instantiate it when you first need it
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
            // This is where options should be added to OkHttpClient like caching rules, cookie handlers, etc
        }
        return okHttpClient;
    }

    public Gson getGson() {
        if (gson == null) {
            gson = new Gson();
            // again you can modify gson here and it will be shared every time you use
        }
        return gson;
    }

    public Retrofit getRetrofit() {
        if (retrofit == null) {
            // force callbacks to return on a background thread
            // important so any operations to modify the returned data and store the returned data is in the background
            // but also, the UI can NOT be updated directly from callbacks, use the MainThreadHandler below
            Executor executor = AsyncTask.THREAD_POOL_EXECUTOR;
            retrofit = new Retrofit.Builder()
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .baseUrl(BASE_URL)
                    .callbackExecutor(executor)
                    .build();
        }
        return retrofit;
    }

    public ApiService getApiService() {
        if (apiService == null) {
            apiService = getRetrofit().create(ApiService.class);
        }
        return apiService;
    }

    public ApiClient getApiClient() {
        if (apiClient == null) {
            apiClient = new ApiClient(getApiService());
        }
        return apiClient;
    }

    // This works but RxJava would be better, but like Dagger has significant upfront complexity
    public Handler getMainThreadHandler() {
        if (mainThreadHandler == null) {
            mainThreadHandler = new Handler(Looper.getMainLooper());
        }
        return mainThreadHandler;
    }

}
