package com.example.retrofit_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateScreen extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_screen);

        recyclerView = findViewById(R.id.recycler_2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateUser();

    }

    public void updateUser() {
        User user = new User("12", "new Title", null);
        Call<User> call = ApiClient.getuserservice().updateUser(2, user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    List<User> data = new ArrayList<>();
                    data.add(response.body());
                    adapter = new RecyclerAdapter2(UpdateScreen.this, data);
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(UpdateScreen.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateScreen.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(UpdateScreen.this, "Failed" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}