package com.example.retrofit_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDetails extends AppCompatActivity {

    EditText edt1, edt2, edt3, edt4;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        edt1 = findViewById(R.id.name);
        edt2 = findViewById(R.id.firstname);
        edt3 = findViewById(R.id.lastname);
        edt4 = findViewById(R.id.email);

        btn = findViewById(R.id.submit_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser(createRequest());

            }
        });

    }

    public UserRequest createRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName(edt1.getText().toString());
        userRequest.setFirstname(edt2.getText().toString());
        userRequest.setLastname(edt3.getText().toString());
        userRequest.setEmail(edt4.getText().toString());

        return userRequest;
    }

    public void saveUser(UserRequest userRequest) {
        Call<UserResponse> userResponseCall = ApiClient.getuserservice().saveUsers(userRequest);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddDetails.this, "Success", Toast.LENGTH_SHORT).show();
                    edt1.setText("");
                    edt2.setText("");
                    edt3.setText("");
                    edt4.setText("");
                    startActivity(new Intent(AddDetails.this, MainActivity.class));

                } else {
                    Toast.makeText(AddDetails.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(AddDetails.this, "Failed" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}