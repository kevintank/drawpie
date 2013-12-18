package com.kevin.sample;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class RanderView extends View {

	Random rand = new Random();
	Handler mHandler = new Handler();
	/**
	 * 오버라이딩 생성자
	 * @param context
	 */
	public RanderView(Context context) {
		super(context);
		
		
	}

	/**
	 * 오버라이딩 생성자
	 * @param context
	 * @param attrs
	 */
	public RanderView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onDraw(final Canvas canvas) {

		/*mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				canvas.drawRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
				invalidate();
				
			}
		}, 1000);*/
		
		final float ZERO = -90f;
		final float DOTONE = 72f;
		float score = 3.8f; //점수. 하드코딩으로 넣었지만 나중에 변경 예정
		 
		float degree = score * DOTONE;
		
		Paint p = new Paint();
		p.setAntiAlias(true);
		p.setStyle(Paint.Style.STROKE);
		p.setStrokeWidth(5);
		p.setAlpha(0x00);
		
		RectF rectF = new RectF(100,100,400,400);
		
		if(score < 2){
			p.setColor(Color.RED);
		}else if(score < 3){
			p.setColor(Color.YELLOW);
		}else if(score < 4){
			p.setColor(Color.BLUE);
		}else if(score < 4){
			p.setColor(Color.GREEN);
		}
		
		canvas.drawArc(rectF, ZERO, degree, false, p);
		
	}
}



























































































































































































































































































































