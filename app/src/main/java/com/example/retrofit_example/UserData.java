package com.example.retrofit_example;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserData extends ViewModel {

    MutableLiveData<List<User>> data = new MutableLiveData<>();

    public MutableLiveData<List<User>> setdata() {
        return data;
    }

    public void getData() {
        Call<List<User>> call = ApiClient.getuserservice().getdata();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> userList = response.body();
                    data.setValue(userList);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }


}
