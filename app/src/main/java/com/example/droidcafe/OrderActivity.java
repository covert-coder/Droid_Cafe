package com.example.droidcafe;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import java.security.PrivateKey;

public class OrderActivity extends AppCompatActivity {
    String mOrderDetails = "No order sent.   Please return to menu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textView = findViewById(R.id.orderText);// assign the TextView to a variable
        Intent intent = getIntent();

        if (intent.hasExtra(MainActivity.EXTRA_MENU_SELECTION)){
            String message = "Order: " +
                    intent.getStringExtra(MainActivity.EXTRA_MENU_SELECTION);
            textView.setTextSize(24);
            textView.setText(message);

        }else{// in case the user arrived by clicking the FAB instead of selecting an item from the menu
            textView.setTextSize(24);
            textView.setText(mOrderDetails);
        }
    }
}