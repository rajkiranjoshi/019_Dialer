package com.example.raj.dialer_019;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class OutgoingCallHandler extends BroadcastReceiver {
    private static Boolean calledEarlier = false;
    public OutgoingCallHandler() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if(!calledEarlier) {
            // Extract phone number reformatted by previous receivers
            String phoneNumber = getResultData();
            if (phoneNumber == null) {
                // No reformatted number, use the original
                phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            }
            Log.i("BC_RECEIVER", "Ph no is " + phoneNumber);
            //Cancel the broadcast
            setResultData(null);

            if (!phoneNumber.equals("")) {
                Uri number = Uri.parse("tel:" + phoneNumber);
                Intent dial = new Intent(Intent.ACTION_DIAL); //Implicit Intent with dial action
                dial.setData(number);
                dial.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                calledEarlier = true;
                context.startActivity(dial);
            }
        }
        else
            calledEarlier = false;

    }
}
