package com.example.retrofit_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    List<User> data;
    FloatingActionButton button;
    UserData model;
    Observer<Call<List<User>>> dataObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.floatingbutton);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        model = new ViewModelProvider(this).get(UserData.class);

//        updateUser();
//        DeleteUser();
//        fetchdata();

//


        model.setdata().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter = new RecyclerAdapter(MainActivity.this, users);
                recyclerView.setAdapter(adapter);
            }
        });
        model.getData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddDetails.class));
            }
        });

    }

//    public void fetchdata() {
//        // Get
//        Call<List<User>> call = ApiClient.getuserservice().getdata();
//        call.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                if (response.isSuccessful()) {
//                    model.setData();
//
//                } else {
//                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


//    public void updateUser() {
//        User user = new User("12", "new Title", null);
//        Call<User> call = ApiClient.getuserservice().udUser(2, user);
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                if (response.isSuccessful()) {
//                    List<User> data = new ArrayList<>();
//                    data.add(response.body());
//                    adapter = new RecyclerAdapter(MainActivity.this, data);
//                    recyclerView.setAdapter(adapter);
//                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Failed" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    public void DeleteUser() {
        Call<Void> call = ApiClient.getuserservice().deleteUser(2);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(MainActivity.this, "Deleted" + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

}