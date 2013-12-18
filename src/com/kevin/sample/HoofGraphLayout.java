/**
 * 
 */
package com.kevin.sample;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * 원그래프를 그려주는 레이아웃 클래스이다.<br>
 * LinearLayout을 상속하여 확장하였으며 <br>
 * 원그래프의 표현하기 위해 직접적으로 사용하는 Layout 클래스 이다.<br>
 * Layout의 최하단 layer에 원그래프가 그려진다.<br>
 * 원그래프의 호를 add(추가)하는 방식으로 그래프를 설정할수있다.<br>
 * @author 오재웅
 *
 */
public class HoofGraphLayout{// extends FrameLayout{
//
//	private float strokScale = 1.0f;
//	
//	public ArrayList<HoofGraph> graphs = new ArrayList<HoofGraph>();
//	
//	/**
//	 * LinearLayout 오버라이딩 생성자
//	 * @param context
//	 */
//	public HoofGraphLayout(Context context) {
//		super(context);
//		init();
//	}
//	
//	/**
//	 * LinearLayout 오버라이딩 생성자
//	 * @param context
//	 * @param attrs
//	 */
//	public HoofGraphLayout(Context context, AttributeSet attrs) {
//		super(context, attrs);
//		init();
//	}
//	
//	/**
//	 * 그래프 초기화
//	 */
//	public void init(){
//		super.setWillNotDraw(false);	
//		strokScale = 1.0f;
//		if(graphs.size()>0){
//			graphs.clear();
//			this.invalidate();
//		}
//		
//	}
//	
//	/**
//	 * Layout이 표한되는 Canvas를 확장<br>
//	 * 원그래프를 내부적으로 Layout에 그려줄수 있게한다.
//	 */
//	public void onDraw(Canvas canvas){
//		super.onDraw(canvas);
//		for(int i=0;i<graphs.size();i++){
//			HoofGraph arc = (HoofGraph)graphs.get(i);
//			arc.setStrokSize(getStrokSize());
//			arc.setSize(this.getWidth(), this.getHeight());
//			arc.draw(canvas);
//		}
//	}
//	
//	/**
//	 * 선택한 호의 에니메이션 효과를 시작한다.
//	 * @param arcIndex 선택할 호의 인덱스
//	 */
//	public void animStart(int arcIndex){
//		new Thread(new AnimDraw(graphs.get(arcIndex))).start();
//	}
//	
//	/**
//	 * 에니메이션이 동적으로 표현하게하는 
//	 * 에니메이션 Thread 클래스 
//	 * @author 오재웅
//	 *
//	 */
//	private class AnimDraw implements Runnable{
//		Arc arc;
//		public AnimDraw(Arc arc){
//			this.arc = arc;
//		}
//		public void run(){
//			float temp_end = arc.end;
//			for(int j=0;j<100;j++){
//				
//				arc.end =  temp_end*j/100;
//		         try {
//		             Thread.sleep(10, 0);
//		         }
//		         catch (InterruptedException e)
//		         {
//		             ;
//		         }
//		         postInvalidate();
//			}
//			arc.end=temp_end;
//			postInvalidate();
//		}
//	}
//	
//	/**
//	 * 원그래프 호의 두께를 받는다.
//	 * @return 호의 두께사이즈
//	 */
//	public int getStrokSize(){
//		float width = getWidth();
//		float height = getHeight();
//		float strok;
//		if(width < height){
//			strok = width;
//		}else{
//			strok = height;
//		}
//		strok=strok/2*strokScale;
//		return (int)strok;
//	}
//
//	/**
//	 * 시작점(각도)에서 끝점(각도)까지<br>
//	 * 해당 컬러로 설정한후 호를 추가한다.<br>
//	 * EX) PieLayout.addArc(0,10,blue).addArc(10,20,red).addArc(20,50,gray); <--호를 3개추가
//	 * @param start 시작점(각도)
//	 * @param end 끝점(각도)
//	 * @param color 컬러
//	 * @param isInvalidate reFresh 여부
//	 * @return 설정된 호의정보 Arc 객체
//	 */
//	public Arc addArc(float start, float end, int color,boolean isInvalidate){
//		Arc arc = new Arc(this);
//		arc.setArc(start, end, color);
//		graphs.add(arc);
//		if(isInvalidate){
//			invalidate();
//		}
//		return arc;
//	}
//	
//	/**
//	 * 시작점(각도)에서 끝점(각도)까지<br>
//	 * 해당 컬러로 설정한후 호를 추가한다.<br>
//	 * EX) PieLayout.addArc(0,10,blue).addArc(10,20,red).addArc(20,50,gray); <--호를 3개추가
//	 * @param start 시작점(각도)
//	 * @param end 끝점(각도)
//	 * @param color 컬러
//	 * @return 설정된 호의정보 Arc 객체
//	 */
//	public Arc addArc(float start, float end, int color){
//		Arc arc = new Arc(this);
//		arc.setArc(start, end, color);
//		graphs.add(arc);
//		invalidate();
//		return arc;
//	}
//	
//	/**
//	 * 원그래프 호두께의 스케일 설정
//	 * @param strokScale 호 두께 스케일값
//	 */
//	public void setScale(float strokScale){
//		this.strokScale = strokScale; 
//	}
//	
//	/**
//	 * 원그래프 호두꼐의 스케일값을 받는데
//	 * @return 스케일값
//	 */
//	public float getStrokScale(){
//		return this.strokScale;
//	}
//	
//	/**
//	 * 추가된 호의 정보객체(Arc)를 가져온다.
//	 * @param index  
//	 * @return Arc 객체
//	 */
//	public Arc getArc(int index){
//		return graphs.get(index); 
//	}
//	
//	/**
//	 * 첫번째 Arc객체를 가져온다.
//	 * @return Arc 객체
//	 */
//	public Arc getArc(){
//		if(graphs.size()>0){
//			return graphs.get(0);
//		}else{
//			return null;
//		}
//	}
//	
//	/**
//	 * 호객체의 갯수를 가져온다.
//	 * @return
//	 */
//	public int getArcCount(){
//		return graphs.size();
//	}
}
