package com.example.mehedi.dfsmania;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Tupleinput extends AppCompatActivity {

    String prevData[]= new String[4];
    String tuple[] = new String[100];

    TextView text;
    int edgeCounter=0;

    int node, edge;
    int dir;

    Boolean sourceNodeSelected = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tupleinput);
        //reccieving data from input.class
        RecievePrevData();
        //edgeCounter=0;
        //TakeInputTuple();


    }

    public void RecievePrevData() {
        //text = (TextView)findViewById(R.id.TtextView);
        //recieving data direction+node+edge
        prevData = getIntent().getExtras().getStringArray("key");
        //text.setText(array[0]+" "+array[1]+" "+array[2]);

        node = Integer.parseInt(prevData[1]);
        edge = Integer.parseInt(prevData[2]);
        dir = Integer.parseInt(prevData[0]);

        setClickableEnd(false);
        setClickableSource(true);

        findViewById(R.id.buttonDone).setClickable(false);
        //findViewById(R.id.buttonDone).setBackgroundColor(Color.DKGRAY);
        //findViewById(R.id.button_back).setBackgroundColor(Color.BLACK);

        text = (TextView)findViewById(R.id.textnode);
        text.setGravity(Gravity.CENTER);


    }


    public void onClick(View v){
        Button button = (Button)v;
        int flag;
        edgeCounter++;
        Log.v("edge counter ", ""+edgeCounter);

        //getting text on click button
        String numString = button.getText().toString();
        int number = Integer.parseInt(numString);


        if(number >= 0 && number <= 9){
            //Toast.makeText(Tupleinput.this, ""+number, Toast.LENGTH_SHORT).show();
            if(number == node){
                sourceNodeSelected = true;
            }

        }

        if(edgeCounter%2 == 1){
            setClickableEnd(true);
            setClickableSource(false);
            flag = 0;  //source clicked
            text.append("\n"+number);
            tuple[edgeCounter] = ""+number; // store number source
            Log.v("edgecounter ", ""+edgeCounter+" "+tuple[edgeCounter]);

            if(prevData[0].equals("0")){
                text.append("   <-->   ");

            }
            else {
                text.append("    -->   ");
            }

        }
        else if(edgeCounter%2 == 0){
            setClickableEnd(false);
            setClickableSource(true);
            flag = 1; //end clicked
            text.append(""+number);
            tuple[edgeCounter] = ""+number; // store number end
            Log.v("edgecounter ", ""+edgeCounter+" "+tuple[edgeCounter]);
        }



        if(edgeCounter/2 == edge){
            setClickableEnd(false);
            setClickableSource(false);
            findViewById(R.id.buttonDone).setClickable(true);
            //findViewById(R.id.buttonDone).setBackgroundColor(Color.GREEN);
        }


    }

    public void onClickDone(View v){

        if(v.getId() == R.id.buttonDone){

            Log.v("source node selected ", " "+sourceNodeSelected);

            if(!sourceNodeSelected){
                Toast.makeText(Tupleinput.this, ""+"Source Node "+node+" has not been Taken", Toast.LENGTH_SHORT).show();
            }

            //Toast.makeText(Tupleinput.this, "Done", Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(getApplicationContext(), canvas_draw.class));
            //going to next page
            else {
                edgeCounter++;
                tuple[edgeCounter] = "" + node;
                edgeCounter++;
                tuple[edgeCounter] = "" + edge;
                tuple[0] = "" + edgeCounter;
                tuple[edgeCounter + 1] = "" + dir;

                edgeCounter -= 2;

                //start draw activity
                Intent i = new Intent(getApplicationContext(), canvas_draw.class);
                //sending data to another activity
                //String s = e1.getText().toString();
                //i.putExtra("name", s);
                /////////
                //sending array on Tupleinput
                Bundle bundel = new Bundle();
                bundel.putStringArray("key", tuple);

                i.putExtras(bundel);

                startActivity(i);
            }




        }
    }
    public void onClickBack(View v){
        if(v.getId() == R.id.button_back){

            findViewById(R.id.buttonDone).setClickable(false);

            if(edgeCounter != 0) {
                if(edgeCounter%2 == 1){ //source was last clicked
                    setClickableSource(true);
                    setClickableEnd(false);

                    text.setText(text.getText().toString().substring(0,text.getText().toString().length()-12));

                }
                else{
                    setClickableSource(false);
                    setClickableEnd(true);

                    text.setText(text.getText().toString().substring(0,text.getText().toString().length()-1));
                }

                edgeCounter--;
            }
        }
    }

    public void setClickableSource(Boolean t){

        findViewById(R.id.digit0).setClickable(t);
        findViewById(R.id.digit1).setClickable(t);
        findViewById(R.id.digit2).setClickable(t);
        findViewById(R.id.digit3).setClickable(t);
        findViewById(R.id.digit4).setClickable(t);
        findViewById(R.id.digit5).setClickable(t);
        findViewById(R.id.digit6).setClickable(t);
        findViewById(R.id.digit7).setClickable(t);
        findViewById(R.id.digit8).setClickable(t);
        findViewById(R.id.digit9).setClickable(t);


/*
        if(t == true){
            findViewById(R.id.digit0).setBackgroundColor(Color.GREEN);
            findViewById(R.id.digit1).setBackgroundColor(Color.GREEN);
            findViewById(R.id.digit2).setBackgroundColor(Color.GREEN);
            findViewById(R.id.digit3).setBackgroundColor(Color.GREEN);
            findViewById(R.id.digit4).setBackgroundColor(Color.GREEN);
            findViewById(R.id.digit5).setBackgroundColor(Color.GREEN);
            findViewById(R.id.digit6).setBackgroundColor(Color.GREEN);
            findViewById(R.id.digit7).setBackgroundColor(Color.GREEN);
            findViewById(R.id.digit8).setBackgroundColor(Color.GREEN);
            findViewById(R.id.digit9).setBackgroundColor(Color.GREEN);
        }
        else{
            findViewById(R.id.digit0).setBackgroundColor(Color.RED);
            findViewById(R.id.digit1).setBackgroundColor(Color.RED);
            findViewById(R.id.digit2).setBackgroundColor(Color.RED);
            findViewById(R.id.digit3).setBackgroundColor(Color.RED);
            findViewById(R.id.digit4).setBackgroundColor(Color.RED);
            findViewById(R.id.digit5).setBackgroundColor(Color.RED);
            findViewById(R.id.digit6).setBackgroundColor(Color.RED);
            findViewById(R.id.digit7).setBackgroundColor(Color.RED);
            findViewById(R.id.digit8).setBackgroundColor(Color.RED);
            findViewById(R.id.digit9).setBackgroundColor(Color.RED);
        }
*/

    }
    public void setClickableEnd(Boolean t){
        findViewById(R.id.end0).setClickable(t);
        findViewById(R.id.end1).setClickable(t);
        findViewById(R.id.end2).setClickable(t);
        findViewById(R.id.end3).setClickable(t);
        findViewById(R.id.end4).setClickable(t);
        findViewById(R.id.end5).setClickable(t);
        findViewById(R.id.end6).setClickable(t);
        findViewById(R.id.end7).setClickable(t);
        findViewById(R.id.end8).setClickable(t);
        findViewById(R.id.end9).setClickable(t);
/*
        if(t == true){
            findViewById(R.id.end0).setBackgroundColor(Color.GREEN);
            findViewById(R.id.end1).setBackgroundColor(Color.GREEN);
            findViewById(R.id.end2).setBackgroundColor(Color.GREEN);
            findViewById(R.id.end3).setBackgroundColor(Color.GREEN);
            findViewById(R.id.end4).setBackgroundColor(Color.GREEN);
            findViewById(R.id.end5).setBackgroundColor(Color.GREEN);
            findViewById(R.id.end6).setBackgroundColor(Color.GREEN);
            findViewById(R.id.end7).setBackgroundColor(Color.GREEN);
            findViewById(R.id.end8).setBackgroundColor(Color.GREEN);
            findViewById(R.id.end9).setBackgroundColor(Color.GREEN);
        }
        else{
            findViewById(R.id.end0).setBackgroundColor(Color.RED);
            findViewById(R.id.end1).setBackgroundColor(Color.RED);
            findViewById(R.id.end2).setBackgroundColor(Color.RED);
            findViewById(R.id.end3).setBackgroundColor(Color.RED);
            findViewById(R.id.end4).setBackgroundColor(Color.RED);
            findViewById(R.id.end5).setBackgroundColor(Color.RED);
            findViewById(R.id.end6).setBackgroundColor(Color.RED);
            findViewById(R.id.end7).setBackgroundColor(Color.RED);
            findViewById(R.id.end8).setBackgroundColor(Color.RED);
            findViewById(R.id.end9).setBackgroundColor(Color.RED);
        }
        */
    }

}