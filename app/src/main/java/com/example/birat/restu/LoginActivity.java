package com.example.birat.restu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by birat on 5/13/17.
 */

public class LoginActivity extends AppCompatActivity {

    public Button b; //for LoginButton
    public EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b = (Button) findViewById(R.id.button);
        username = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().trim().length() <= 0|| password.getText().toString().trim().length() <= 0) {
                        Toast.makeText(LoginActivity.this, "Please enter both Username and Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    login();
                }
            }
        });
    }

    public void login() {

        final String user_name = username.getText().toString().trim();
        final String pass_word = password.getText().toString().trim();

        SharedPreferences sharedPreference=getSharedPreferences("DeviceToken",MODE_PRIVATE);
        final String token = sharedPreference.getString("token","");

        SharedPreferences sharedPreferences = getSharedPreferences("LoggedInUser",MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sharedPreferences.edit();
        spEditor.putString("username",user_name);
        spEditor.apply();

        final ProgressDialog loading = ProgressDialog.show(this, "Checking Your Credentials", "Please wait...", false, false);
        //  RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.customerlogin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        //if the server response is success
                        if (response.equalsIgnoreCase("success")) {
                            Log.d("LoginResponse", response);
                            // storeUserData(phoneNo,password);
                            Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));

                        } else {
                            Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                        }
                        // startActivity(new Intent(Login.this, Login.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding the parameters password and username
                params.put("username", user_name);
                params.put("password", pass_word);
                //params.put("token",token);
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    public void gotoDashboard(View view){
        Intent nextPage = new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(nextPage);
    }

    public void gotoForgotPass(View view){
        Intent nextPage = new Intent(LoginActivity.this, ForgotPassActivity.class);
        startActivity(nextPage);
    }

    public void gotoSignUpNew(View view){
        Intent nextPage = new Intent(LoginActivity.this, SignUpNewActivity.class);
        startActivity(nextPage);
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
