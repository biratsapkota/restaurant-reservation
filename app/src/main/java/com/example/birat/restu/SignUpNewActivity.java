
package com.example.birat.restu;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.app.Activity;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


public class SignUpNewActivity extends AppCompatActivity {
    private EditText fullName, email, contact_no, password,edCode;
    private Button signUp,getCode;
    AppCompatButton buttonConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_new);

        fullName = (EditText) findViewById(R.id.editText3);
        email = (EditText) findViewById(R.id.editText9);
        contact_no = (EditText) findViewById(R.id.editText10);
        password = (EditText) findViewById(R.id.editText6);
        signUp = (Button) findViewById(R.id.button2);
        getCode = (Button) findViewById(R.id.button1);

        getCode.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if (fullName.getText().toString().trim().length() <= 0 || email.getText().toString().trim().length() <= 0 || contact_no.getText().toString().trim().length() <= 0) {
                    Toast.makeText(SignUpNewActivity.this, "Please fill all the information", Toast.LENGTH_SHORT).show();
                }
                else getCode();
            }
        });
        signUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullName.getText().toString().trim().length() <= 0 || email.getText().toString().trim().length() <= 0 || contact_no.getText().toString().trim().length() <= 0) {
                    Toast.makeText(SignUpNewActivity.this, "Please fill all the information", Toast.LENGTH_SHORT).show();
                } else {
                      Toast.makeText(SignUpNewActivity.this, "New Account Created", Toast.LENGTH_SHORT).show();
                      startActivity(new Intent(SignUpNewActivity.this, LoginActivity.class));
                }
            }
        });
    }
    private void getCode() {

        final String full_name = fullName.getText().toString().trim();
        final String email1 = email.getText().toString(); //use trim if necessary
        final String contact = contact_no.getText().toString();
        final String pass_word = password.getText().toString();


        // final String TAG_RESPONSE= "ErrorMessage";
        // RequestQueue requestQueue = Volley.newRequestQueue(Register.this);

        final ProgressDialog loading = ProgressDialog.show(this, "Getting Code", "Please wait...", false, false);
        loading.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.getmeacode,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Log.d("myAPP", response);

                        try {
                            if (response.equalsIgnoreCase("success")) {
                                //        pDialog.dismiss();
                                Toast.makeText(SignUpNewActivity.this, response, Toast.LENGTH_LONG).show();
                                confirmCode(full_name, email1, contact,pass_word);
                            } else {
                                //Displaying a toast if the otp entered is wrong
                                Toast.makeText(SignUpNewActivity.this, response, Toast.LENGTH_LONG).show();
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
                        Toast.makeText(SignUpNewActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding the parameters to the request
                params.put("mobile_number", contact);
                //  params.put("name", name);
                return params;
            }
        };
        //Adding request the the queue
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
    private void confirmCode(final String name,final String email1, final String number, final String password) {

        //Creating a LayoutInflater object for the dialog box
        LayoutInflater li = LayoutInflater.from(this);
        //Creating a view to get the dialog box
        View confirmDialog = li.inflate(R.layout.dialog_confirm, null);

        //Initializing confirm button fo dialog box and edit text of dialog box
        buttonConfirm = (AppCompatButton) confirmDialog.findViewById(R.id.buttonConfirm);
        edCode = (EditText) confirmDialog.findViewById(R.id.editTextOtp);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        //Adding our dialog box to the view of alert dialog
        alert.setView(confirmDialog);

        //Creating an alert dialog
        final AlertDialog alertDialog = alert.create();

        //Displaying the alert dialog
        alertDialog.show();

        //On the click of the confirm button from alert dialog
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hiding the alert dialog
                alertDialog.dismiss();

                //Displaying a progressbar
                //pDialog.show();

                //Getting the user entered otp from edittext
                final String code = edCode.getText().toString().trim();

                // RequestQueue requestQueue = Volley.newRequestQueue(Register.this);

                //Creating an string request
                StringRequest stringRequest = new StringRequest(Request.Method.POST,Config.registeracustomer,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //if the server response is success
                                if (response.equalsIgnoreCase("success")) {
                                    //dismissing the progressbar
                                    //pDialog.dismiss();
                                    //Starting a new activity
                                    startActivity(new Intent(SignUpNewActivity.this, LoginActivity.class));

                                } if (response.equalsIgnoreCase("failed")){
                                    //Displaying a toast if the otp entered is wrong
                                    Toast.makeText(SignUpNewActivity.this, "Wrong Code Please Try Again", Toast.LENGTH_LONG).show();

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                alertDialog.dismiss();
                                Toast.makeText(SignUpNewActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        //Adding the parameters otp and username
                        params.put("code", code);
                        params.put("full_name", name);
                        params.put("mobile_number", number);
                        params.put("password", password);
                        return params;

                    }
                };
                //Adding the request to the queue
                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, LoginActivity.class));
    }
}




      /*  signUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullName.getText().toString().trim().length() <= 0 || email.getText().toString().trim().length() <= 0 || contact_no.getText().toString().trim().length() <= 0) {
                    Toast.makeText(SignUpNewActivity.this, "Please fill all the information", Toast.LENGTH_SHORT).show();
                } else {
                    signup_new();
                    Toast.makeText(SignUpNewActivity.this, "New Account Created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpNewActivity.this, LoginActivity.class));
                }
            }
        });
    }

    public void signup_new() {

        final String full_name = fullName.getText().toString().trim();
        final String email1 = email.getText().toString(); //use trim if necessary
        final String contact = contact_no.getText().toString();
        final String pass_word = password.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Config.registeracustomer,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //if the server response is success
                        if (response.equalsIgnoreCase("success")) {
                            Log.d("LoginResponse", response);
                            Toast.makeText(SignUpNewActivity.this, "Please connect to internet", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUpNewActivity.this, LoginActivity.class));
                        }
                        else {
                            startActivity(new Intent(SignUpNewActivity.this, SignUpNewActivity.class));
                            Toast.makeText(SignUpNewActivity.this, response, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                @Override
                    public void onErrorResponse(VolleyError error) {
                    Toast.makeText(SignUpNewActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", full_name);
                params.put("email", email1);
                params.put("mobile_number", contact);
                params.put("password", pass_word);
                return params;
            }
        };
        //Adding the request to the queue
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private void confirmOtp(final String name, final String number, final String password) {

        //Creating a LayoutInflater object for the dialog box
        LayoutInflater li = LayoutInflater.from(this);
        //Creating a view to get the dialog box
        View confirmDialog = li.inflate(R.layout.dialog_confirm, null);

        //Initizliaing confirm button fo dialog box and edit text of dialog box
        buttonConfirm = (AppCompatButton) confirmDialog.findViewById(R.id.buttonConfirm);
        editTextOtp = (EditText) confirmDialog.findViewById(R.id.editTextOtp);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        //Adding our dialog box to the view of alert dialog
        alert.setView(confirmDialog);

        //Creating an alert dialog
        final AlertDialog alertDialog = alert.create();

        //Displaying the alert dialog
        alertDialog.show();

        //On the click of the confirm button from alert dialog
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hiding the alert dialog
                alertDialog.dismiss();

                //Displaying a progressbar
                //pDialog.show();

                //Getting the user entered otp from edittext
                final String otp = editTextOtp.getText().toString().trim();

                // RequestQueue requestQueue = Volley.newRequestQueue(Register.this);

                //Creating an string request
                StringRequest stringRequest = new StringRequest(Request.Method.POST,Config.registeracustomer,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //if the server response is success
                                if (response.equalsIgnoreCase("success")) {
                                    //dismissing the progressbar
                                    //pDialog.dismiss();
                                    //Starting a new activity
                                    startActivity(new Intent(Register.this, Login.class));

                                } if (response.equalsIgnoreCase("failed")){
                                    //Displaying a toast if the otp entered is wrong
                                    Toast.makeText(Register.this, "Wrong OTP Please Try Again", Toast.LENGTH_LONG).show();

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                alertDialog.dismiss();
                                Toast.makeText(Register.this, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        //Adding the parameters otp and username
                        params.put("code", otp);
                        params.put("full_name", name);
                        params.put("mobile_number", number);
                        params.put("password", password);
                        return params;
                    }
                };
                //Adding the request to the queue
                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, LoginActivity.class));
    }
}



*/



