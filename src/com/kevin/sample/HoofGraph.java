package com.kevin.sample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
 

/**
 * @author   Administrator
 */
public class HoofGraph extends View {
 	private int l = 84;
 	private int r = 84;
 	private int  x = 40;  // left margin
 	private int  y = 32;  // top margin
 	private int lineWidth = 16;  // hootGraph의 굵기.
 	private int percent = 0;
 	private int dpScale=2;       // 모든 값이 Dp로만 받기 때문에 화면은 pixel로 변형하기 우한 Scale
 	
 	private float triangleSize = 5f;  // 삼각형의 크기 배율 클수록 확대됨. 
 	// percent 표시를 하는 Font Size
 	private float fontSize     = 13f;  
 	
 	Path path ;
 	Paint pnt;
	Paint trianglePaint;

	
	
	
	
	//삼각형 좌표
	float []dx = new float[3];
	float []dy = new float[3];
	
	
 	Canvas cn;
 	
 	/**
	 * @param  percent
	 * @uml.property  name="percent"
	 */
 	public void setPercent(int percent){
 		this.percent = percent;
 	}
 	
 	
	public HoofGraph(Context context) {
		super(context);
    	this.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
    	init();
    	initTriangle();
	}
	
	public HoofGraph(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
		init();
	}
	
	private void init() {
		this.l = this.l* this.dpScale;
	 	this.r = this.r* this.dpScale;
	 	this.x = this.x*this.dpScale;
	 	this.y = this.y*this.dpScale;
	 	this.lineWidth = this.lineWidth*this.dpScale;
	 	this.fontSize  = this.fontSize * this.dpScale;
		this.triangleSize = this.triangleSize * this.dpScale;
		initTriangle();
	}
	/**
	 * 삼각형 Paint설정 정보 초기화.
	 */
	private void initTriangle() {
	    // TODO Auto-generated method stub
		trianglePaint = new Paint();
		trianglePaint.setColor(Color.WHITE);
		trianglePaint.setStyle(Paint.Style.FILL);
		dx[0] = 0;
		dy[0] = 0;
		dx[1] = -triangleSize;
		dy[1] = -(float) (triangleSize* Math.tan(Math.toRadians(30)));
		dx[2] = -triangleSize;
		dy[2] =  (float) (triangleSize* Math.tan(Math.toRadians(30)));
    }
	
	private int getTotalLength(){
		return (int)((2*l) + Math.PI*r);
	}
	
	private int getSubLength(){
		return getTotalLength()*percent/100;
	}
	
	/**
	 * @param  scale
	 * @uml.property  name="dpScale"
	 */
	public void setDpScale(int scale) {
		this.l = this.l/ this.dpScale;
	 	this.r = this.r/ this.dpScale;
	 	this.x = this.x/this.dpScale;
	 	this.y = this.y/this.dpScale;
	 	this.lineWidth = this.lineWidth/this.dpScale;
	 	this.fontSize  = this.fontSize / this.dpScale;
		this.triangleSize = this.triangleSize / this.dpScale;
		this.dpScale = scale;
		init();
	}
	
	private int check(){
		int check = 1;
		int subLength = getSubLength();
		if(subLength>this.l){
			if( subLength <  Math.PI*r+l){
				check = 2;
			}else {
				check = 3;
			}
		}
		Log.d("draw path test","check="+check);
		return check;
	}
	private int getSubLength(int i){
		return getTotalLength()*i/100;
	}
	
	private int check(int i){
		int check = 1;
		int subLength = getSubLength(i);
		if(subLength>this.l){
			if( subLength <  Math.PI*r+l){
				check = 2;
			}else {
				check = 3;
			}
		}
		Log.d("draw path test2","check="+check+"="+i+"="+subLength+"="+this.getSubLength());
		return check;
	}
	
	private void drawSubLine(int i) {
		// TODO Auto-generated method stub
		path.reset();
		path.moveTo(this.x, this.y);
		// path move start position
		path.rMoveTo(0, l+r);
		
		
		Paint textPaint = new Paint();
		textPaint.setAntiAlias(true);
		textPaint.setColor(Color.WHITE);
		textPaint.setTextSize(fontSize);
		
		
		float tx = this.x-this.lineWidth/2;
		float ty = this.y+l+r;
		switch(check(i)){
		
		case 1:
			path.rLineTo(0, - getSubLength(i));
			ty = ty -getSubLength(i);
			textPaint.setTextAlign(Paint.Align.RIGHT);
			cn.drawText(String.valueOf(percent),(float) (tx-triangleSize-fontSize/5*3),ty+fontSize/3,textPaint);	
			textPaint.setTextSize(fontSize/2);
			textPaint.setTextAlign(Paint.Align.LEFT);
			cn.drawText("%",(float) (tx-triangleSize-fontSize/5*3),ty,textPaint);
			break;
			
		case 2:
			path.rLineTo(0, -l);
			// path 반원 추가
			RectF subRf = new RectF();
			subRf.set(x, y, x+2*r, y+2*r);
			
			int al = this.getSubLength(i) - l;
			int subAngle = (int) (180*al/(Math.PI*r));
			double radians = Math.toRadians(subAngle);

			path.addArc(subRf,180,subAngle);
			/////////////////////////////////////////
			//삼각형을 그리기 위한 좌표 계산 영역
			/////////////////////////////////////////
			tx =  (float) (tx + (1- Math.cos(Math.toRadians(subAngle)))* (r+lineWidth/2));
			ty =  (float) (ty - l - (r+lineWidth/2)*Math.sin(radians));
			
			float x1 = dx[1] ; 
			float y1 = dy[1];
			dx[1] =  (float) (x1 * Math.cos(radians) - y1 * Math.sin(radians));
			dy[1] =  (float) (x1 * Math.sin(radians) + y1 * Math.cos(radians));
			

			x1 = dx[2]; 
			y1 = dy[2];
			dx[2] =  (float) (x1 * Math.cos(radians) - y1 * Math.sin(radians));
			dy[2] =  (float) (x1 * Math.sin(radians) + y1 * Math.cos(radians));
			
			float positionX = (float) (tx+(dx[1]+dx[2])/2-triangleSize*Math.cos(radians)+fontSize/4);
			float positionY = (float) (ty+(dy[1]+dy[2]-triangleSize*Math.sin(radians))/2);
			textPaint.setTextAlign(Paint.Align.RIGHT);
			cn.drawText(String.valueOf(percent),positionX,positionY,textPaint);
			textPaint.setTextAlign(Paint.Align.LEFT);
			textPaint.setTextSize(fontSize/2);
			cn.drawText("%",positionX,positionY-fontSize*1/3, textPaint);
			
			break;
		case 3:
			// path line start 
			path.rLineTo(0, -l);
			// path 반원 추가
			RectF rf = new RectF();
			rf.set(x, y, x+2*r, y+2*r);
			path.addArc(rf,180,180);
			// path line add
			int sl =(int)( this.getSubLength(i) - l-Math.PI*r);
			path.moveTo(x+2*r, y+r);

			path.rLineTo(0, sl);
			/////////////////////////////////////////
			//삼각형을 그리기 위한 좌표 계산 영역
			/////////////////////////////////////////
			tx = tx + 2*(r+lineWidth/2);
			ty = ty - l + sl;
			dx[1] = -dx[1];
			dx[2] = -dx[2];
			
			
			
			
			float positionX1 = (float) (tx + triangleSize+fontSize*1.5);
			textPaint.setTextAlign(Paint.Align.RIGHT);
			cn.drawText(String.valueOf(percent),positionX1,ty+fontSize/3,textPaint);
			
			
			textPaint.setTextAlign(Paint.Align.LEFT);
			textPaint.setTextSize(fontSize/2);
			cn.drawText("%",positionX1,ty, textPaint);
			break;
		}
		
		cn.drawPath(path, pnt);

		path.reset();
		path.moveTo(tx,ty);

		for(int j=0;j<3;j++){
			path.lineTo(tx+dx[(j+1)%3],ty+dy[(j+1)%3]);
		}
		
		path.close();
		
		cn.drawPath(path, trianglePaint);
		
	}
	
	public void onDraw(Canvas canvas)
	{
		this.cn = canvas;
		//canvas.drawColor(Color.WHITE);
		
		path = new Path();
		/*
		// path init
		path.reset();
		path.moveTo(x, y);
		
		// path move start position
		path.rMoveTo(0, l+r);
		// path line start 
		path.rLineTo(0, -l)
		;*/
		// path 반원 추가
		RectF rf = new RectF();
		rf.set(x, y, x+2*r, y+2*r);
		/*
		path.addArc(rf,180,180);
		// path line add
		path.rLineTo(0, l);
		// Effect 효과
    	EmbossMaskFilter emboss = new EmbossMaskFilter(new float[]{-1,-1,1},0.7f,3,4);
*/
    	//Paint 속성 주
		pnt = new Paint();
		pnt.setAntiAlias(true);
		pnt.setStrokeWidth(lineWidth);
		//pnt.setColor(Color.MAGENTA);
		pnt.setStyle(Paint.Style.STROKE);
		//pnt.setStrokeCap(Cap.ROUND);
		//pnt.setMaskFilter(emboss);
		
		
//		canvas.drawPath(path, pnt);
		//path.reset();
		//this.getSubPath(path);
		pnt.setColor(Color.WHITE);
		
		/*path.moveTo(this.x, this.y);
		// path move start position
		path.rMoveTo(0, l+r);*/
		this.drawSubLine(percent);
		
//		this.x;
//		this.r+this.l+this.lineWidth;
		if(this.percent > 0 ) {
			Paint p = new Paint();
			p.setStyle(Paint.Style.FILL);
			p.setColor(Color.WHITE);
			p.setAntiAlias(true);
//			RectF oval = new RectF(0,r+l+y, x*2, r+l+y);
			RectF oval = new RectF(x-(lineWidth/2),r+y+l-(lineWidth/2), x+(lineWidth/2), r+l+y+(lineWidth/2));
			
			
			canvas.drawArc(oval,0,180,true, p);
			
		}
		
	}
	
}
