package com.example.mehedi.dfsmania;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import java.net.PasswordAuthentication;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;

/**
 * Created by mehedi on 11-Apr-16.
 */
public class MyView extends View implements View.OnTouchListener
{
    protected int drawcount=0;

    String prevData[] = new String[100];
    Boolean isNodePresent[] = new Boolean[10];
    Boolean isCirclePresent[] = new Boolean[10];
    LinkedList<Integer> graph[] = new LinkedList[10];
    LinkedList<Pair<Integer, Integer> > nodeDetails[]= new LinkedList[10];


    int edgeCounter=0;
    int node, edge;
    int dir;
    int circleCounter=0;
    int prevSx, prevSy;  //store previous source centres
    int prevEx, prevEy;
    int counterNode=0;
    int prevS, prevE;

    int division = 1;
    int incrementer=4;


    float prevX, prevY;
    Timer timer;
    int tempx, tempy;


    int ss=0;
    int dd=0;
    int setDrawfirst =1;
    Boolean stopSimulation = false;
    int prevNode;
    //int redColored[] = new int[10];
    LinkedList<Integer> path = new LinkedList();
    LinkedList<Integer> storeVisited = new LinkedList();





    public MyView(Context context, String prevDataCanvas[]) {
        super(context);



        //initialize new linked list
        for(int i =0; i<10 ;i++){
            graph[i] = new LinkedList();
            nodeDetails[i] = new LinkedList();
        }

        //end of that

        //nodeDetails[0].add(Pair.create(10, 20));

//        Log.v("node details ", "repeat " + nodeDetails[0].get(0).first + " " + nodeDetails[0].get(0).second + "  " + nodeDetails[0].indexOf(4));



        Arrays.fill(isNodePresent, false);
        Arrays.fill(isCirclePresent, false);
        circleCounter=0;

        //reciving data
        prevData = prevDataCanvas;
        RecievePrevData();


    }

    public void init(){
        path.clear();
        for(int i =0;i<10; i++){
            nodeDetails[i].clear();
            //redColored[i] = 0;
        }
        storeVisited.clear();


    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN) {
            Log.v("touch  ", "touched..........................................................");
        }
        return true;
    }
    @Override
    protected  void onDraw(Canvas can){




        super.onDraw(can);

        setBack(can);

        if(setDrawfirst == 1) {
            //restore eerything after recv data

            init();
            /////////
            Draw(can);
            Draw(can);
            dfs(can);//store path
            if(path.size() == 1) {
                stopSimulation = true;
            }

            counterNode=0;
            if(path.size()>1) {
                prevS = (int) path.get(counterNode);
            }
            //>>>>>>>>
            storeVisited.add(prevS);

            Log.v("logggggg dource", ""+prevS);
            if(!nodeDetails[prevS].isEmpty()) {
                prevSx = nodeDetails[prevS].get(0).first;
                prevSy = nodeDetails[prevS].get(0).second;
            }
            counterNode++;
            if(path.size()>1) {
                prevE = (int) path.get(counterNode);
            }
            //>>>>>>>>
            //storeVisited.add(prevE);

            if(!nodeDetails[prevE].isEmpty() && path.size()>1) {
                prevEx = nodeDetails[prevE].get(0).first;
                prevEy = nodeDetails[prevE].get(0).second;
            }
            //checck path
            for(int i =0; i<path.size();i++){

                Log.v("path check--- ","ind: "+i+" node: "+counterNode);
            }
            /////
        }

        setDrawfirst = 0;

        tempx=1;
        tempy=1;
        int xx, yy, len;
        //if(prevSx <= prevEx  && prevSy <= prevSy){ //1
        len = (int)Math.sqrt((double)((prevSx-prevEx)*(prevSx-prevEx) + (prevSy-prevEy)*(prevSy-prevEy)));
        if(len == 0){
            len = 10;
        }
        if(!nodeDetails[prevS].isEmpty() && !nodeDetails[prevE].isEmpty()) {
            tempx = ((division * prevEx) + (len - division) * prevSx) / len;
            tempy = ((division * prevEy) + (len - division) * prevSy) / len;
        }


        division+=incrementer;

        Paint paint1 = new Paint();
        paint1.setColor(Color.GREEN);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(6);
        paint1.setAntiAlias(true);
        Log.v("in dfs simula ondraw", "" + ss);
        can.drawCircle(tempx, tempy, 30, paint1);





        //}
        /*else if(prevSx <= prevEx  && prevSy >= prevEy){ //2

        }
        else if(prevSx >= prevEx  && prevSy <= prevSy){ //3

        }
        else if(prevSx >= prevEx  && prevSy >= prevEy){ //4

        }*/

        if(Math.abs(tempx-prevEx)<=1 && Math.abs(tempy - prevEy)<=1){
            prevS = prevE;
            prevSx = prevEx;
            prevSy = prevEy;
            counterNode++;
            if(path.size() > counterNode) {
                prevE = (int) path.get(counterNode);
                storeVisited.add(prevS);
                //redColored[prevS]++;
            }
            else{

                stopSimulation = true;
            }
            if(!nodeDetails[prevE].isEmpty()) {
                prevEx = nodeDetails[prevE].get(0).first;
                prevEy = nodeDetails[prevE].get(0).second;
            }
            division = 1;
            Log.v("logggggg source end ", "" + prevS + "   " + prevE);
            //delay.........
            //SystemClock.sleep(2000);
            ///............

        }

        //animate hereeeeeeeeeeeeeeeeeeeee
        /////#########################
        /*Paint paint1 = new Paint();
        paint1.setColor(Color.RED);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(6);
        paint1.setAntiAlias(true);
        Log.v("in dfs simula ondraw", ""+ss);
        ss+=2;
        dd+=2;*/
        /*if(!nodeDetails[0].isEmpty()) {
            can.drawCircle(nodeDetails[0].get(0).first+ss, nodeDetails[0].get(0).second+dd, 30, paint1);
        }*/
        //#############

        Draw(can);
        //<><><<><><><><>paint red all visited continuously
        if(path.size()>1) {
            paintRedVisitedCirclesLines(can);
        }
        else{
            circleRedDraw(path.get(0), can);
        }
        //,.,.,.,.,
        if(!stopSimulation ) {
            invalidate();
        }
    }

    public void setBack(Canvas can){
        Rect rec = new Rect();
        rec.set(0,0,can.getWidth(), can.getHeight());

        Paint pan = new Paint();
        pan.setColor(Color.rgb(111,80,44));
        pan.setStyle(Paint.Style.FILL);

        can.drawRect(rec, pan);
    }
    public void paintRedVisitedCirclesLines(Canvas can){
        //line pain
        Paint paintLine = new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setStrokeWidth(6);
        paintLine.setColor(Color.RED);

        int x1, y1;

        for(int i =0;i<storeVisited.size();i++){
            //circleRedDraw(storeVisited.get(i), can);
            if(i != storeVisited.size()-1){
                can.drawLine(nodeDetails[storeVisited.get(i)].get(0).first, nodeDetails[storeVisited.get(i)].get(0).second, nodeDetails[storeVisited.get(i+1)].get(0).first, nodeDetails[storeVisited.get(i+1)].get(0).second, paintLine);
                /*if(redColored[storeVisited.get(i)] > 1){
                    circleGreenDraw(storeVisited.get(i), can);
                }
                else {
                    circleGreenDraw(storeVisited.get(i), can);
                }*/
            }
            if( i == storeVisited.size() - 1  && path.size()>1) {
                can.drawLine(nodeDetails[storeVisited.get(i)].get(0).first, nodeDetails[storeVisited.get(i)].get(0).second, tempx, tempy, paintLine);
                /*if(redColored[storeVisited.get(i)] > 1){

                }
                else {

                }*/
                //circleRedDraw(storeVisited.get(i), can);
            }
            circleRedDraw(storeVisited.get(i), can);


        }
        circleRedDraw(storeVisited.get(0), can);
    }
    public void circleRedDraw(int v, Canvas can ){
        //visied circle paint
        Paint paintVisited = new Paint();
        paintVisited.setAntiAlias(true);
        paintVisited.setColor(Color.RED);
        //................
        int x1 = nodeDetails[v].get(0).first;
        int y1 = nodeDetails[v].get(0).second;

        can.drawCircle(x1, y1, 30, paintVisited);

        //text paint
        Paint paintText = new Paint();

        paintText.setColor(Color.GREEN);
        paintText.setTextSize(24f);
        paintText.setAntiAlias(true);
        paintText.setTextAlign(Paint.Align.CENTER);
        //text draw

        Rect bounds = new Rect();
        String text=""+v;
        paintText.getTextBounds(text, 0, text.length(), bounds);
        can.drawText(text, x1, y1, paintText);


    }

    public void circleGreenDraw(int v, Canvas can){
        // previous visied circle paint
        Paint paintVisited = new Paint();
        paintVisited.setAntiAlias(true);
        paintVisited.setColor(Color.YELLOW);
        //................
        int x1 = nodeDetails[v].get(0).first;
        int y1 = nodeDetails[v].get(0).second;

        can.drawCircle(x1, y1, 30, paintVisited);

        //text paint
        Paint paintText = new Paint();

        paintText.setColor(Color.GREEN);
        paintText.setTextSize(24f);
        paintText.setAntiAlias(true);
        paintText.setTextAlign(Paint.Align.CENTER);
        //text draw

        Rect bounds = new Rect();
        String text=""+v;

        Rect  bouding = new Rect();
        String test1 = ""+v;
        Paint pint1 = new Paint();


        pint1.setAntiAlias(true);
        pint1.setColor(Color.GREEN);

        pint1.setTextAlign(Paint.Align.CENTER);





        paintText.getTextBounds(text, 0, text.length(), bounds);
        can.drawText(text, x1, y1, paintText);
    }

    protected void Draw(Canvas can)
    {
        drawcount++;

        //super.onDraw(can);
        //background
        setBackgroundColor(Color.WHITE);
        //line pain
        Paint paintLine = new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setStrokeWidth(6);
        paintLine.setColor(Color.BLACK);
        //text paint
        Paint paint = new Paint();

        paint.setColor(Color.GREEN);
        paint.setTextSize(24f);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        //..
        //circle paint
        //Paint paint = new Paint();
        Paint circlePaint = new Paint();

        //for circle
        circlePaint.setColor(Color.BLACK);
        circlePaint.setAntiAlias(true);
        //....


        for(int i =0;i<10; i++){
            for(int j =0; j<graph[i].size();j++){
                Log.v("check graph list middle","s "+i+" e "+graph[i].get(j));
                if(!isNodePresent[i]) {
                    drawSeriallyCircle("" + i, can, circlePaint);
                }
                if( !isNodePresent[graph[i].get(j)]){
                    drawSeriallyCircle("" + graph[i].get(j), can, circlePaint);
                }


            }
        }

        for(int i =0;i<10; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                if(!nodeDetails[i].isEmpty() && !nodeDetails[graph[i].get(j)].isEmpty()) {
                    int x1 = nodeDetails[i].get(0).first;
                    int y1 = nodeDetails[i].get(0).second;
                    int x2 = nodeDetails[graph[i].get(j)].get(0).first;
                    int y2 = nodeDetails[graph[i].get(j)].get(0).second;

                    can.drawLine(x1, y1, x2, y2, paintLine);
                    //curve if directed
                    if(dir == 1){
                        //directed graph
                        int Px = x2;
                        int Py = y2;
                        //int length = (int)Math.sqrt((double)((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)));
                        int length = 70;

                        if(Px < x1){
                            Px+=length;

                        }

                    }
                    //draw text
                    //1st
                    Rect bounds = new Rect();
                    String text=""+i;
                    paint.getTextBounds(text, 0, text.length(), bounds);
                    can.drawText(text, x1, y1, paint);
                    //2nd

                    text=""+graph[i].get(j);
                    paint.getTextBounds(text, 0, text.length(), bounds);
                    can.drawText(text, x2, y2, paint);
                    //
                    Log.v("centers Line ", x1 + " " + y1 + " " + x2 + " " + y2);
                }
            }
        }

/*
        for(int i =0;i<10; i++){
            for(int j =0; j<nodeDetails[i].size();j++){
                Log.v("graph node cntr", "s " + i + " e "+j+"  " + nodeDetails[i].get(j).first +"  "+nodeDetails[i].get(j).second);
            }
        }
*/
        //dfs  will be here
        //dfs(can);
        //Draw(can, 1);
        //checkkkkkkkkkkkkkkkkkkkkkkk
        /*
        Paint paint1 = new Paint();
        paint1.setColor(Color.RED);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(6);
        paint1.setAntiAlias(true);
        Log.v("in dfs simula ondraw", ""+ss);
        ss++;
        dd++;
        can.drawCircle(ss, dd, 30, paint1);
        */
        //...........................................
        //invalidate();

        //.............................................
        circleCounter=0;

        Arrays.fill(isNodePresent, false);
        Arrays.fill(isCirclePresent, false);
        /*
        for(int i =0;i<10; i++){
            nodeDetails[i].clear();
        }
        */

/*
        for(int i =1; i<=node; i++){
            drawSeriallyCircle("" + i, can);
        }
  */

        drawcount++;
        Log.v("reeat check ", "repeat "+drawcount);


        //new DFSsimulation(getContext(), nodeDetails, graph);


    }


    public void drawSeriallyCircle(String text, Canvas can, Paint circlePaint){

        int x = getWidth()-220;
        int y = getHeight()-40;
        //int halfX = x/2;
        //int halfY = y/2;
        int Xportion = x/5;
        int Yportion = y/4;

        int initX = 0;
        int initY = y/2;

        circleCounter= circleCounter%10;
        Log.v("circle counter ",""+circleCounter);
        if(circleCounter==1 && !isCirclePresent[circleCounter]) {
            drawCircle(text, initX + 60, initY, can, circlePaint);
            isNodePresent[Integer.parseInt(text)] = true;
            if(setDrawfirst==1) {
                nodeDetails[Integer.parseInt(text)].add(Pair.create(initX + 60, initY));
            }
            isCirclePresent[circleCounter]= true;
        }
        else if(circleCounter==2 && !isCirclePresent[circleCounter]){
            drawCircle(text, initX+5*Xportion-60, initY, can, circlePaint);
            isNodePresent[Integer.parseInt(text)] = true;
            if(setDrawfirst==1) {
                nodeDetails[Integer.parseInt(text)].add(Pair.create(initX + 5 * Xportion - 60, initY));
            }
            isCirclePresent[circleCounter]= true;
        }

        else if(circleCounter==3 && !isCirclePresent[circleCounter]) {
            drawCircle(text, initX + 3 * Xportion, initY - 2 * Yportion + 40, can, circlePaint);
            isNodePresent[Integer.parseInt(text)] = true;
            if(setDrawfirst==1) {
                nodeDetails[Integer.parseInt(text)].add(Pair.create(initX + 3 * Xportion, initY - 2 * Yportion + 40));
            }
            isCirclePresent[circleCounter]= true;
        }
        else if(circleCounter==4 && !isCirclePresent[circleCounter]) {
            drawCircle(text, initX + 2 * Xportion, initY + 2 * Yportion, can, circlePaint);
            isNodePresent[Integer.parseInt(text)] = true;
            if(setDrawfirst==1) {
                nodeDetails[Integer.parseInt(text)].add(Pair.create(initX + 2 * Xportion, initY + 2 * Yportion));
            }
            isCirclePresent[circleCounter]= true;
        }
        else if(circleCounter==5 && !isCirclePresent[circleCounter]) {
            drawCircle(text, initX + 3 * Xportion, initY + 2 * Yportion, can, circlePaint);
            isNodePresent[Integer.parseInt(text)] = true;
            if(setDrawfirst==1) {
                nodeDetails[Integer.parseInt(text)].add(Pair.create(initX + 3 * Xportion, initY + 2 * Yportion));
            }
            isCirclePresent[circleCounter]= true;
        }
        else if(circleCounter==6 && !isCirclePresent[circleCounter]) {
            drawCircle(text, initX + 2 * Xportion, initY - 2 * Yportion + 40, can, circlePaint);
            isNodePresent[Integer.parseInt(text)] = true;
            if(setDrawfirst==1) {
                nodeDetails[Integer.parseInt(text)].add(Pair.create(initX + 2 * Xportion, initY - 2 * Yportion + 40));
            }
            isCirclePresent[circleCounter]= true;
        }
        else if(circleCounter==7 && !isCirclePresent[circleCounter]) {
            drawCircle(text, initX + 4 * Xportion, initY - Yportion, can, circlePaint);
            isNodePresent[Integer.parseInt(text)] = true;
            if(setDrawfirst==1) {
                nodeDetails[Integer.parseInt(text)].add(Pair.create(initX + 4 * Xportion, initY - Yportion));
            }
            isCirclePresent[circleCounter]= true;
        }
        else if(circleCounter==8 && !isCirclePresent[circleCounter]) {
            drawCircle(text, initX + Xportion, initY + Yportion + 20, can, circlePaint);
            isNodePresent[Integer.parseInt(text)] = true;
            if(setDrawfirst==1) {
                nodeDetails[Integer.parseInt(text)].add(Pair.create(initX + Xportion, initY + Yportion + 20));
            }
            isCirclePresent[circleCounter]= true;
        }
        else if(circleCounter==9 && !isCirclePresent[circleCounter]) {
            drawCircle(text, initX + Xportion, initY - Yportion, can, circlePaint);
            isNodePresent[Integer.parseInt(text)] = true;
            if(setDrawfirst==1) {
                nodeDetails[Integer.parseInt(text)].add(Pair.create(initX + Xportion, initY - Yportion));
            }
            isCirclePresent[circleCounter]= true;
        }
        else if(circleCounter==0 && !isCirclePresent[circleCounter]) {
            drawCircle(text, initX + 4 * Xportion, initY + Yportion + 20, can, circlePaint);
            isNodePresent[Integer.parseInt(text)] = true;
            if(setDrawfirst==1) {
                nodeDetails[Integer.parseInt(text)].add(Pair.create(initX + 4 * Xportion, initY + Yportion + 20));
            }
            isCirclePresent[circleCounter]= true;
        }
        circleCounter++;

    }

    public void drawCircle(String text, int Cx, int Cy, Canvas can, Paint circlePaint){  //Cx=centre x, Cy = centre y



        can.drawCircle(Cx, Cy, 30, circlePaint);

    }

    public void RecievePrevData() {
        //text = (TextView)findViewById(R.id.TtextView);
        //recieving data direction+node+edge
        //prevData = getIntent().getExtras().getStringArray("key");
        //text.setText(array[0]+" "+array[1]+" "+array[2]);
        edgeCounter = Integer.parseInt(prevData[0]);

        node = Integer.parseInt(prevData[edgeCounter-1]);
        edge = Integer.parseInt(prevData[edgeCounter]);
        dir = Integer.parseInt(prevData[edgeCounter+1]);
        int x,y;
        for(int i = 1; i <=edgeCounter-2; i+=2){
            x = Integer.parseInt(prevData[i]);
            y = Integer.parseInt(prevData[i+1]);
            isNodePresent[x]=true;
            isNodePresent[y]=true;
            if(dir == 0){
                graph[x].add(y);
                graph[y].add(x);
            }
            else{
                graph[x].add(y);
            }
        }
        //check--------------
        for(int i =0;i<10; i++){
            for(int j =0; j<graph[i].size();j++){
                Log.v("check graph list ","s "+i+" e "+graph[i].get(j));
            }
        }

        //check--------------


        Log.v("in myview  ", "edgecounter " + edgeCounter + " node " + node + " edge " + edge);



    }

    public void dfs(Canvas can){
        boolean visited[] = new boolean[10];
        path.clear();

        int src=node;
        prevNode = src;
        /*if(!nodeDetails[src].isEmpty()) {
            prevX = nodeDetails[src].get(0).first;
            prevY = nodeDetails[src].get(0).second;
        }*/

        DFSUtil(src, visited, can);
    }

    void DFSUtil(int v,boolean visited[], Canvas can)
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        //......................................................
        /*
        float xx = 0;
        float yy = 0;
        float inc_x, inc_y, len;
        if(!nodeDetails[v].isEmpty()) {
            xx = nodeDetails[v].get(0).first;
            yy = nodeDetails[v].get(0).second;
        }

        //mark blue vertex v
        makeBlue(v, can);

        if(xx != prevX && yy != prevY){
            Log.v("dfs inside line draw "," "+xx+ "  "+prevX);
            len = (float)Math.sqrt((double)(prevX-xx)*(prevX-xx) + (prevY-yy)*(prevY-yy));

            //inc_x = Math.abs((prevX-xx))/len;
            //inc_y = Math.abs((prevY-yy))/len;

            Paint paintLinerev = new Paint();
            paintLinerev.setAntiAlias(true);
            paintLinerev.setColor(Color.BLUE);
            paintLinerev.setStrokeWidth(6);

            //
            //can.drawLine(prevX, prevY, xx, yy, paintLinerev);

            float tempx, tempy;

            for(int i =1 ; i <= len ; i++) {
                tempx = (i*xx + (len-i)*prevX)/(len);
                tempy = (i*yy + (len-i)*prevY)/(len);
                can.drawLine(prevX, prevY, tempx, tempy, paintLinerev);
                int j =1;
                //invalidate();


            }


        }*/
        //..............................................................

        //checkkkkkkkkkkkkkkkkkkkkkkk
        /*
        Paint paint1 = new Paint();
        paint1.setColor(Color.RED);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(6);
        paint1.setAntiAlias(true);
        Log.v("in dfs simula ondraw", ""+ss);
        ss++;
        dd++;
        if(!nodeDetails[v].isEmpty()) {
            can.drawCircle(nodeDetails[v].get(0).first+ss, nodeDetails[v].get(0).second+dd, 30, paint1);
        }
        */
        //Draw(can);
        //...........................................

        //invalidate();


        //make line animated

        Log.v("dfs inside ","current "+v);
        path.add(v);
        //.................................................................

        //System.out.print(v+" ");

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = graph[v].listIterator();
        while (i.hasNext())
        {
            int n = i.next();
            if (!visited[n]) {
                DFSUtil(n, visited, can);
                ///....................................
                //red color for back
                Log.v("dfs inside ", "current " + v);
                path.add(v);
                //......................................
            }
        }

    }
    public void makeBlue(int v, Canvas can){
        if(!nodeDetails[v].isEmpty()) {
            //text
            Paint paint = new Paint();

            paint.setColor(Color.GREEN);
            paint.setTextSize(24f);
            paint.setAntiAlias(true);
            paint.setTextAlign(Paint.Align.CENTER);
            //..
            //circle paint
            //Paint paint = new Paint();
            Paint paintVertex = new Paint();

            //for circle
            paintVertex.setColor(Color.BLUE);
            paintVertex.setAntiAlias(true);
            drawCircle("" + v, nodeDetails[v].get(0).first, nodeDetails[v].get(0).second, can, paintVertex);

            //....

            //draw text
            //1st
            Rect bounds = new Rect();
            String text = "" + v;
            paint.getTextBounds(text, 0, text.length(), bounds);

            can.drawText(text, nodeDetails[v].get(0).first, nodeDetails[v].get(0).second, paint);

        }

    }

}
