package com.example.droidCafeInput;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnCreateContextMenuListener {

    public static final String  EXTRA_MENU_SELECTION  = "MENU_KEY";
    String mOrderMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView  droidImage = findViewById(R.id.droidCafe_icon);
        registerForContextMenu(droidImage);

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
        // Handled action bar item clicks here.

        switch (item.getItemId()){
            case R.id.action_contact:
                toastDisplay((getString(R.string.action_contact_message)));
                break;
            case R.id.action_favourites:
                toastDisplay((getString(R.string.action_favorites_message)));
                break;
            case R.id.action_order:
                Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
                orderIntent.putExtra(EXTRA_MENU_SELECTION, mOrderMessage);
                startActivity(orderIntent);
                return false;
                //toastDisplay(getString(R.string.action_order_message));
            case R.id.action_status:
                toastDisplay(getString(R.string.action_status_message));
                break;
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home_page:
                displayToast("home page  clicked.");
                return true;
            case R.id.action_about_droidCafe:
                displayToast("About droid cafe  clicked.");
                return true;
            case R.id.action_Copyright:
                displayToast("copyright  clicked.");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    public void displayToast(String message){
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();    }
}