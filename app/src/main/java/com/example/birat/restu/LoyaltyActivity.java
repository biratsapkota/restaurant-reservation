package com.example.birat.restu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoyaltyActivity extends AppCompatActivity {

    private TextView loyalty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loyalty);

        loyalty = (TextView)findViewById(R.id.loyalty);


    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, DashboardActivity.class));
    }
}
