package com.example.birat.restu;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.NumberPicker;
import android.view.View.OnClickListener;
import android.widget.Toast;

/*import android.app.TimePickerDialog;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;*/
import android.widget.TimePicker;
import android.widget.DatePicker;

public class TableBookActivity extends AppCompatActivity {
    private NumberPicker num;
    private Button book;
    private TimePicker simpleTimePicker;
    private DatePicker simpleDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_book);
        //for numberPicker
        num = (NumberPicker) findViewById(R.id.numberPicker2);
        num.setMinValue(1);
        num.setMaxValue(20);
        num.setWrapSelectorWheel(true);
        simpleDatePicker.setMinDate(System.currentTimeMillis() - 1000);
        book = (Button) findViewById(R.id.button5);

      //for EditText

        simpleTimePicker = (TimePicker) findViewById(R.id.simpleTimePicker);
        simpleTimePicker.setIs24HourView(true); // used to display time in 24 hours

        simpleDatePicker = (DatePicker) findViewById(R.id.simpleDatePicker);

        addListenerOnButton();
    }
    public void addListenerOnButton() {

        book.setOnClickListener(new OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                if ((simpleTimePicker.getHour() <= 5 && simpleTimePicker.getHour() >= 23) || (simpleDatePicker.getYear() <= 2017)) {
                    Toast.makeText(TableBookActivity.this, "Invalid Entry", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TableBookActivity.this, "Booked", Toast.LENGTH_SHORT).show();
                    Intent nextPage = new Intent(TableBookActivity.this, DashboardActivity.class);
                    startActivity(nextPage);

                }
            }
        });
    }
}
