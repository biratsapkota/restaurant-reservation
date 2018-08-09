package com.example.birat.restu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

    }

    public void gotoService(View view){
        Intent nextPage = new Intent(DashboardActivity.this, ServiceActivity.class);
        startActivity(nextPage);
    }

    public void gotoTableBook(View view){
        Intent nextPage = new Intent(DashboardActivity.this, TableBookActivity.class);
        startActivity(nextPage);
    }
    public void gotoDishes(View view){
        Intent nextPage = new Intent(DashboardActivity.this, DishesActivity.class);
        startActivity(nextPage);
    }
    public void gotoReferAndEarn(View view){
        Intent nextPage = new Intent(DashboardActivity.this, ReferAndEarnActivity.class);
        startActivity(nextPage);
    }
    public void gotoLoyalty(View view){
        Intent nextPage = new Intent(DashboardActivity.this, LoyaltyActivity.class);
        startActivity(nextPage);
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
