package com.example.retrofit_example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    @POST("posts")
    Call<UserResponse> saveUsers(@Body UserRequest userRequest);

    @GET("posts")
    Call<List<User>> getdata();

    @PUT("posts/{id}")
    Call<User> udUser(@Path("id") int id, @Body User user);

    @PATCH("posts/{id}")
    Call<User> updateUser(@Path("id") int id, @Body User user);

    @DELETE("posts/{id}")
    Call<Void> deleteUser(@Path("id") int id);

}
