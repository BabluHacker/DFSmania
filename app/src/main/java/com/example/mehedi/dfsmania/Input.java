package com.example.mehedi.dfsmania;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class Input extends AppCompatActivity {
    //TextView t;
    RadioGroup radio_g;
    RadioButton radio_b;
    Button button_nxt;
    EditText Tnode, Tedge;
    String node="0", edge="0";
    String Finaletext[] = new String[4];
    Boolean radio_click=false, node_click=false, edge_click = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        //onRecieve();
        //findViewById(R.id.button_next).setBackgroundColor(Color.GREEN);
        //(RadioGroup)findViewById(R.id.radioGroup).findViewById(R.id.radioButton1).check
        onClickListenerButton();
    }
    /*public void onRecieve(){
        t = (TextView)findViewById(R.id.textView);
        Intent ii = getIntent();
        Bundle ib = ii.getExtras();
        String s = (String)ib.get("name");
        t.setText(s);
    }*/

    public void onClickRandom(View v){
        String prevData[] = new String[100];
        int nodecounter=0;
        Boolean isNode[] = new Boolean[10];
        Arrays.fill(isNode, false);
        if(v.getId() == R.id.button_random){
            Random rand = new Random();
            int edge = rand.nextInt(9);
            int node = 0;
            /*if(node < 1){
                node *= -1;
            }
            node = node%9 +1;
            */
            if(edge < 1) {
                edge *=-1;

            }
            edge = edge%9 + 1;
            int i;
            for(i =1 ; i<= edge ;){
                int x = rand.nextInt(10);
                int y = rand.nextInt(10);
                if(x < 1){
                    x*=-1;
                }
                if(y < 1){
                    y*=-1;
                }
                x = x%10;
                y = y%10;

                if(i==1){
                    node = x;
                }
                prevData[i] = ""+x;
                i++;
                prevData[i] = ""+y;
                i++;





            }
            prevData[i] = ""+node;
            i++;
            prevData[i] = ""+edge;
            //i++;
            prevData[i+1] = "0";
            prevData[0] = ""+i;

            Intent ii = new Intent(getApplicationContext(), canvas_draw.class);
            //sending data to another activity
            //String s = e1.getText().toString();
            //i.putExtra("name", s);
            /////////
            //sending array on Tupleinput
            Bundle bundel = new Bundle();
            bundel.putStringArray("key", prevData);

            ii.putExtras(bundel);

            startActivity(ii);

        }

    }



    public void onClickListenerButton(){
        radio_g = (RadioGroup)findViewById(R.id.radioGroup);
        button_nxt = (Button)findViewById(R.id.button_next);
        Tnode = (EditText)findViewById(R.id.editText1);
        Tedge = (EditText)findViewById(R.id.editText2);



        button_nxt.setOnClickListener(new View.OnClickListener() {

               public void onClick(View v) {
                   radio_click=node_click=edge_click = false;

                   //for radio button
                   int selected_id = radio_g.getCheckedRadioButtonId();

                   if(radio_g.getCheckedRadioButtonId() == -1){
                       Toast.makeText(Input.this, "Select Direction", Toast.LENGTH_SHORT).show();
                       onClickListenerButton();
                   }

                   radio_b = (RadioButton) findViewById(selected_id);



                   if(radio_b.getText().toString().matches("")){
                       Toast.makeText(Input.this, "Select Direction", Toast.LENGTH_SHORT).show();
                       onClickListenerButton();

                   }
                   else if(radio_b.getText().toString().matches("Undirected")){

                       Finaletext[0] = "0";
                       radio_click=true;
                   }
                   else if(radio_b.getText().toString().matches("Directed")){
                       Finaletext[0] = "1";
                       radio_click=true;
                   }

                   //for node edge text
                   node = Tnode.getText().toString();
                   if(!node.equals("") && (node.equals("0") || node.equals("1") || node.equals("2") || node.equals("3") || node.equals("4") || node.equals("5") || node.equals("6") || node.equals("7") || node.equals("8") || node.equals("9"))){
                       node_click=true;
                   }
                   edge = Tedge.getText().toString();
                   if(!edge.equals("") && !edge.equals("0") && edge.length() == 1){
                       edge_click=true;
                   }

                   Finaletext[1] = node;
                   Finaletext[2] = edge;


                   Log.v(" in input", "node :" + node);
                   Log.v(" in input", "edge :" + edge);
                   //for check
                   for(int i =0; i<3; i++) {
                       Log.v(" in input", "Finaletext :" + Finaletext[i]);
                   }



                   //Toast.makeText(Input.this, radio_b.getText().toString(), Toast.LENGTH_SHORT).show();
                   if(radio_click && node_click && edge_click) {
                       Intent i = new Intent(getApplicationContext(), Tupleinput.class);
                       //sending data to another activity
                       //String s = e1.getText().toString();
                       //i.putExtra("name", s);
                       /////////
                       //sending array on Tupleinput
                       Bundle bundel = new Bundle();
                       bundel.putStringArray("key", Finaletext);

                       i.putExtras(bundel);

                       startActivity(i);
                   }

                   else if(!node_click){
                       Toast.makeText(Input.this, "Source must be between 0 to 9", Toast.LENGTH_SHORT).show();
                       onClickListenerButton();
                   }
                   else if(!edge_click){
                       Toast.makeText(Input.this, "Edges must be between 1 to 9", Toast.LENGTH_SHORT).show();
                       onClickListenerButton();

                   }
                   else{

                       Toast.makeText(Input.this, "Fill up the Blanks correctly", Toast.LENGTH_SHORT).show();
                       onClickListenerButton();
                   }
               }
           }
        );

    }


}
