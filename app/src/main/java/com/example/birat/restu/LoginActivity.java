package com.example.birat.restu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


/**
 * Created by birat on 5/13/17.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void gotoDashboard(View view){
        Intent nextPage = new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(nextPage);
    }


}
