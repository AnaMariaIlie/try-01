package ro.pub.cs.systems.eim.practicaltest01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01SecondaryActivity extends AppCompatActivity {

    TextView t;
    Button ok;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_secondary);

        t = findViewById(R.id.t);
        ok = findViewById(R.id.ok);
        cancel = findViewById(R.id.cancel);

        Intent i = getIntent();
        if (i != null) {
            int t1 = i.getIntExtra("text1", -1);
            int t2 = i.getIntExtra("text1", -1);
            System.out.println("set text");
            t.setText(String.valueOf(t1+t2));
        }

        ok.setOnClickListener(buttonClickListener);
        cancel.setOnClickListener(buttonClickListener);

    }


    private Button1ClickListener buttonClickListener = new Button1ClickListener();
    private class Button1ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ok:
                    setResult(1, new Intent());
                    finish();
                    break;
                case R.id.cancel:
                    setResult(-1, new Intent());
                    finish();
                    break;

            }
        }
    }
}
