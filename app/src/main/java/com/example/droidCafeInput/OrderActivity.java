package com.example.droidCafeInput;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String mOrderDetails = "No order sent.   Please return to menu";
    private TextView callText;
    private String spinnerLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        Spinner phoneSpinner = findViewById(R.id.label_spinner);
        if(phoneSpinner != null){
            phoneSpinner.setOnItemSelectedListener(this);
            // createFromResource has as parameters, (context, textArrayResourceId int, textViewResourceId int)
            // textArrayResource Id is the strings array we created in Strings.xml called Labels_array
            // the textViewResourceId, android.R.layout.simple_spinner_item is a built in resource for spinners
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.Labels_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource
                    (android.R.layout.simple_spinner_dropdown_item);// sets the layout for the dropdown list
            phoneSpinner.setAdapter(adapter);// applies our ArrayAdapter to the spinner for the dropdown list
        }

        TextView textView = findViewById(R.id.orderText);// assign the TextView to a variable
        Intent intent = getIntent();
        // if the intent received is from MainActivity and has the key "EXTRA_MENU_SELECTION"
        if (intent.hasExtra(MainActivity.EXTRA_MENU_SELECTION)){
            // assign the content of the received intent to a String called message
            String message = "Order: " +
                    intent.getStringExtra(MainActivity.EXTRA_MENU_SELECTION);
            textView.setTextSize(24);
            // put the received message into the textView
            textView.setText(message);

        }else{// in case the user arrived by clicking the FAB instead of selecting an item from the menu
            textView.setTextSize(24);
            textView.setText(mOrderDetails);
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()){
            case R.id.sameday:
                if (checked)
                    displayToast(getString(R.string.same_day_delivery_selected));
                break;
            case R.id.nextday:
                if (checked)
                    displayToast(getString(R.string.next_day_delivery_selected));
                break;
            case R.id.pickup:
                if (checked)
                    displayToast(getString(R.string.Pickup_delivery_selected));
                break;
        }
    }
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

        if (position != 0) {
            // assign the position of the item in the dropdown to a String variable, spinnerLabel
            spinnerLabel = adapterView.getItemAtPosition(position).toString();

            displayToast(spinnerLabel);

        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void callUs(View view) {
        Uri number = Uri.parse("tel:4034836652");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }

}