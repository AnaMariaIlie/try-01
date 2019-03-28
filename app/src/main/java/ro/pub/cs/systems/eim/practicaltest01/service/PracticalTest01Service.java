package ro.pub.cs.systems.eim.practicaltest01.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Service extends Service {

    ProcessingThread processingThread;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        System.out.println("service invoked");
        processingThread =  new ProcessingThread(this, Double.valueOf(intent.getStringExtra("ma")));
        processingThread.start();
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            processingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
