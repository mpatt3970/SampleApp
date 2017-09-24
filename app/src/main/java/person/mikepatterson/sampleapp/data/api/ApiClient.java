package person.mikepatterson.sampleapp.data.api;

import person.mikepatterson.sampleapp.data.pojo.BlurbResponse;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Difference between this class and the service class is that service is more like an interface
 * This class can define constants for some calls
 */
public class ApiClient {

    private ApiService apiService;

    public ApiClient(ApiService apiService) {
        this.apiService = apiService;
    }

    public void getBlurbs(Callback<BlurbResponse> callback) {
        Call<BlurbResponse> call = apiService.getBlurbs();
        call.enqueue(callback);
    }
}
