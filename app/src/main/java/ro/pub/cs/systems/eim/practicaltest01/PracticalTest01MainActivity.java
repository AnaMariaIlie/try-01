package ro.pub.cs.systems.eim.practicaltest01;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ro.pub.cs.systems.eim.practicaltest01.service.BReceiver;
import ro.pub.cs.systems.eim.practicaltest01.service.Constants;
import ro.pub.cs.systems.eim.practicaltest01.service.PracticalTest01Service;

public class PracticalTest01MainActivity extends AppCompatActivity {

    Button nav;
    Button b1;
    Button b2;
    TextView t1;
    TextView t2;
    int c1=0;
    int c2=0;
    int prag = 3;
    double ma;
    BReceiver br = new BReceiver();
    IntentFilter startedServiceIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);


        nav = findViewById(R.id.nav);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);

        b1.setOnClickListener(buttonClickListener);
        b2.setOnClickListener(buttonClickListener);
        nav.setOnClickListener(buttonClickListener);

        startedServiceIntentFilter = new IntentFilter();
        startedServiceIntentFilter.addAction(Constants.ACTION_STRING);


    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.b1:
                    c1++;
                    t1.setText(String.valueOf(Integer.parseInt(t1.getText().toString()) + 1));

                    System.out.println(c1+c2);
                    if(c1+c2 > prag) {
                        Intent intent = new Intent();
                        ma = (double)(c1+c2)/2;
                        intent.putExtra("ma", String.valueOf(ma));
                        //intent.setComponent(new ComponentName(getApplicationContext(), PracticalTest01Service.class));
                        intent.setComponent(new ComponentName("ro.pub.cs.systems.eim.practicaltest01.service", "ro.pub.cs.systems.eim.practicaltest01.service.PracticalTest01Service"));
                        intent.setAction("ro.cs.pub.service.STRING");
                        startService(intent);
                    }
                    break;

                case R.id.b2:
                    c2++;
                    t2.setText(String.valueOf(Integer.parseInt(t2.getText().toString()) + 1));
                    break;
                case R.id.nav:
                    System.out.println("NAAAAAAV");
                    Intent i = new Intent(getApplicationContext(), PracticalTest01SecondaryActivity.class);
                    i.putExtra("text1", Integer.valueOf(t1.getText().toString()));
                    i.putExtra("text2", Integer.valueOf(t2.getText().toString()));
                    startActivityForResult(i, 1);
                    break;

            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("text1", t1.getText().toString());
        savedInstanceState.putString("text2", t2.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.getString("text1") != null) {
            t1.setText(savedInstanceState.getString("text1"));
        }

        if (savedInstanceState.getString("text2") != null) {
            t2.setText(savedInstanceState.getString("text2"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
                Toast.makeText(this, String.valueOf(resultCode), Toast.LENGTH_SHORT).show();
            }

    }


    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(br);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(br, startedServiceIntentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent();
        stopService(intent.setComponent(new ComponentName("ro.pub.cs.systems.eim.practicaltest01.service", "ro.pub.cs.systems.eim.practicaltest01.service.PracticalTest01Service")));
    }
}
