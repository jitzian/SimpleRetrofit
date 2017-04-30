package test.simple.example.raian.com.org.verysimpleretrofit.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import test.simple.example.raian.com.org.verysimpleretrofit.model.ResultGithub;

/**
 * Created by raian on 4/30/17.
 */

public interface RestService {

    @GET("users/{user}/repos")
    Call<List<ResultGithub>> getRepos(@Path("user")String userName);

}
