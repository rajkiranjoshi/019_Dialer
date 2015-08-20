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
//        setContentView(R.layout.activity_dialer_interface);
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        Log.i("DialerInterFace","Action: "+action);
        Log.i("DialerInterface","ActionType: "+type);

        Uri data = intent.getData();
        String data_str = data.toString();
        String num = data_str.substring(data_str.indexOf(':')+1,data_str.length());
        Log.i("DialerInterface","Number: "+num);
//        TextView textV = (TextView) findViewById(R.id.ph_num);
//        textV.setText(num);
        String newNum = modifyNum(num);
        Log.i("DialerInterface","New Number: "+newNum);
        if (!newNum.equals("")) {
            Uri number = Uri.parse("tel:" + newNum);
            Intent dial = new Intent(Intent.ACTION_CALL);
            dial.setData(number);
            OutgoingCallHandler.calledEarlier = true;
            startActivity(dial);
            finish();
        }

    }

    private static String modifyNum(String num){
        String newNum = "";
        if(num.length()==13 && num.substring(0,3).equals("+91")){ //India number being called
            Log.i("DialerInterface","India number");
            newNum = "019"+num.substring(1,num.length());
        }
        else if(num.length()==12 && num.substring(0,2).equals("+1")) { //US number being called
            Log.i("DialerInterface", "US number");
            newNum = "019"+num.substring(1,num.length());
        }
        else{
            Log.i("DialerInterface", "Error with the number");
            newNum = "";
            //TODO show a error pop-up
        }
        return newNum;
    }



}
