package com.example.sushil.actionbar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowActivity extends Activity {
    ImageView v1;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        v1=(ImageView) findViewById(R.id.imageView3);
        t1=(TextView)findViewById(R.id.textView4);

        Intent i=getIntent();
        String s=i.getStringExtra("y");
        t1.setText(s);

       int s1=i.getIntExtra("x",0);
        v1.setImageResource(s1);
    }
}
