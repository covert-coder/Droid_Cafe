package com.example.droidcafe;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String  EXTRA_MENU_SELECTION  = "MENU_KEY";
    String mOrderMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // the following methods are called from onClick's in the fragment_first xml
    public void showDonutOrder(View view) {
        toastDisplay(getString(R.string.donut_order_message));
        mOrderMessage = getString(R.string.donut_order_message);
        sendIntent(mOrderMessage);
    }
    public void showIceCreamOrder(View view) {
        toastDisplay(getString(R.string.ice_cream_order_message));
        mOrderMessage = getString(R.string.ice_cream_order_message);
        sendIntent(mOrderMessage);
    }
    public void showFroyoOrder(View view) {
        toastDisplay(getString(R.string.froyo_order_message));
        mOrderMessage = getString(R.string.froyo_order_message);
        sendIntent(mOrderMessage);
    }
    // each of the above three methods calls this toast maessage adding its own message
    private void toastDisplay(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    public void sendIntent(String orderMessage){
        Intent intentOrder = new Intent(MainActivity.this, OrderActivity.class);
        intentOrder.putExtra(EXTRA_MENU_SELECTION, orderMessage);
        startActivity(intentOrder);
    }

}