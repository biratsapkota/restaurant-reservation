package com.example.birat.restu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ForgotPassActivity extends AppCompatActivity {

    private EditText contact;
    private Button getCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        contact = (EditText) findViewById(R.id.editText8);
        getCode = (Button) findViewById(R.id.btn_getCode);

        getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contact.getText().toString().trim().length() <= 0) {
                    Toast.makeText(ForgotPassActivity.this, "Please enter contact number", Toast.LENGTH_SHORT).show();
                }
                else {
                    get_code();
                    startActivity(new Intent(ForgotPassActivity.this, LoginActivity.class));
                }
            }
        });
    }

    public void get_code(){
        final String contact1 = contact.getText().toString();



        // final String TAG_RESPONSE= "ErrorMessage";
        // RequestQueue requestQueue = Volley.newRequestQueue(Register.this);

        final ProgressDialog loading = ProgressDialog.show(this, "Getting Code", "Please wait...", false, false);
        loading.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.forgotpassword,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Log.d("myAPP", response);

                        try {
                            if (response.equalsIgnoreCase("success")) {
                                //        pDialog.dismiss();
                                Toast.makeText(ForgotPassActivity.this, response, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(ForgotPassActivity.this, response, Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(ForgotPassActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding the parameters to the request
                params.put("mobile_number", contact1);
                //  params.put("name", name);
                return params;
            }
        };
        //Adding request the the queue
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }
}