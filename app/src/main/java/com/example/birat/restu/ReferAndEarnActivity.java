package com.example.birat.restu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ReferAndEarnActivity extends AppCompatActivity {
    private Button rfr;
    private EditText fullName, mobile,email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_and_earn);

        //assigning id
        fullName = (EditText)findViewById(R.id.editText4);
        mobile = (EditText)findViewById(R.id.editText5);
        email = (EditText)findViewById(R.id.editText12);
        rfr = (Button)findViewById(R.id.button);

        rfr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullName.getText().toString().trim().length() <= 0 || email.getText().toString().trim().length() <= 0 || mobile.getText().toString().trim().length() <= 0) {
                    Toast.makeText(ReferAndEarnActivity.this, "Please fill all the information", Toast.LENGTH_SHORT).show();
                } else {
                    refer();
                    Toast.makeText(ReferAndEarnActivity.this, "Referred", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ReferAndEarnActivity.this, LoginActivity.class));
                }
            }
        });
    }
    public void refer() {

        final String full_name = fullName.getText().toString().trim();
        final String email1 = email.getText().toString(); //use trim if necessary
        final String contact = mobile.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Config.referandearn,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //if the server response is success
                        if (response.equalsIgnoreCase("success")) {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ReferAndEarnActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", full_name);
                params.put("email", email1);
                params.put("contact", contact);
                return params;
            }
        };
        //Adding the request to the queue
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, DashboardActivity.class));
    }
}
