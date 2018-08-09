package com.example.birat.restu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoLogin(View view) {
        Intent newpage = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(newpage);
    }


   // to close app
   @Override
   public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you want to close app?");
        builder.setCancelable(true);
        builder.setNegativeButton("No",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                dialogInterface.cancel();
            }
        });
       builder.setPositiveButton("Yes",new DialogInterface.OnClickListener(){
           @Override
           public void onClick (DialogInterface dialogInterface, int i){
               Intent homeIntent = new Intent(Intent.ACTION_MAIN);
               homeIntent.addCategory( Intent.CATEGORY_HOME );
               homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               startActivity(homeIntent);
           }
       });
       AlertDialog alertDialog = builder.create();
       alertDialog.show();
   }
}