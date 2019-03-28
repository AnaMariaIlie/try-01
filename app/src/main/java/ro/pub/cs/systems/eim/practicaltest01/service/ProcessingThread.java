package ro.pub.cs.systems.eim.practicaltest01.service;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.time.LocalDateTime;
import java.util.Calendar;

class ProcessingThread extends Thread {

    private double ma;
    private Context context;


    public ProcessingThread(Context context, double ma) {
        this.context = context;
        this.ma = ma;
    }

    @Override
    public void run() {

        while(true) {
            sendMessage(Constants.ACTION_STRING);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessage(String actionString) {

        Intent intent = new Intent(Constants.ACTION_STRING);
        intent.putExtra(Constants.DATA, Calendar.getInstance().getTime().toString() + " " +ma );
        context.sendBroadcast(intent);
    }
}
