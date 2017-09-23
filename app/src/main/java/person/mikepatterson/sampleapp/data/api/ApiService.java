package person.mikepatterson.sampleapp.data.api;

import person.mikepatterson.sampleapp.data.pojo.BlurbResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("blurbs")
    Call<BlurbResponse> getBlurbs();
}
