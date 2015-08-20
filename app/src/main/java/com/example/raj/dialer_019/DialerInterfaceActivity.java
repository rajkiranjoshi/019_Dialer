package com.example.raj.dialer_019;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.regex.Pattern;

public class DialerInterfaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialer_interface);
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        Log.i("DialerInterFace","Action: "+action);
        Log.i("DialerInterface","ActionType: "+type);

        Uri data = intent.getData();
        String data_str = data.toString();
        String num = data_str.substring(data_str.indexOf(':')+1,data_str.length());
        Log.i("DialerInterface","Number: "+num);
        TextView textV = (TextView) findViewById(R.id.ph_num);
        textV.setText(num);

        if (!num.equals("")) {
            Uri number = Uri.parse("tel:" + num);
            Intent dial = new Intent(Intent.ACTION_CALL);
            dial.setData(number);
            startActivity(dial);
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dialer_interface, menu);
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

}
