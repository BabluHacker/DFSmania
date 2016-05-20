package com.example.mehedi.dfsmania;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class canvas_draw extends AppCompatActivity {
    String prevData[] = new String[100];
    int edgeCounter;
    int node, edge;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        RecievePrevData();
        //setContentView(R.layout.activity_canvas_draw);
        setContentView(new MyView(this, prevData));


    }

    public void RecievePrevData() {
        //text = (TextView)findViewById(R.id.TtextView);
        //recieving data direction+node+edge
        prevData = getIntent().getExtras().getStringArray("key");
        //text.setText(array[0]+" "+array[1]+" "+array[2]);
        edgeCounter = Integer.parseInt(prevData[0]);

        node = Integer.parseInt(prevData[edgeCounter-1]);
        edge = Integer.parseInt(prevData[edgeCounter]);

    }
}
