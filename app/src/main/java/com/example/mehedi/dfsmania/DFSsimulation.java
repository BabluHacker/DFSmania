package com.example.mehedi.dfsmania;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import java.util.LinkedList;

/**
 * Created by mehedi on 22-Apr-16.
 */
public class DFSsimulation extends View{

    int cout=0;

    LinkedList<Integer> graph[] = new LinkedList[10];
    LinkedList<Pair<Integer, Integer>> nodeDetails[]= new LinkedList[10];

    public DFSsimulation(Context context, LinkedList nodeDetails[], LinkedList graph[]) {
        super(context);
        cout++;
        Log.v("in dfs simulation", "" + cout);


    }
    @Override
    public void onDraw(Canvas can){
        super.onDraw(can);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        Log.v("in dfs simula ondraw", "" + cout);

        can.drawCircle(nodeDetails[0].get(0).first, nodeDetails[0].get(0).second, 40, paint);

    }

}
