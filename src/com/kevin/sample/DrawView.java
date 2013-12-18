package com.kevin.sample;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class DrawView extends View {
    Paint paint = new Paint();
    float  x= 200.0f;
    float y = 210.0f;
    float div = 2.0f;
    Context  context ;
    public DrawView(Context context) {
        super(context);
    	this.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        this.context = context;
        paint.setColor(Color.BLACK);
    }

    @Override
    public void onDraw(Canvas canvas) {
    	/*
    	Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(0, y/div);
        path.lineTo(x/div, y);
        path.lineTo(x, y/div);
        path.lineTo(x, 0);
        path.lineTo(0, 0);
        */
    	EmbossMaskFilter emboss = new EmbossMaskFilter(new float[]{1,1,1},0.5f,1,1);
        BlurMaskFilter blur = new BlurMaskFilter( 10, BlurMaskFilter.Blur.NORMAL );

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        //paint.setDither(true);
        paint.setColor(Color.RED);
        
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
        
        paint.setStrokeWidth(40);
        paint.setMaskFilter(emboss);
        
        Path point = new Path();
        point.moveTo(500, 500);
        point.lineTo(501, 501);
        canvas.drawPath(point,paint);
        
        Path path = new Path();
        path.moveTo(50,50);
        path.lineTo(x,y);
        
        canvas.drawPath(path,paint);
        canvas.drawLine(50, 0, 310, 230, paint);
        //canvas.drawPath(path, paint);
    }

}