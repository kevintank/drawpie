package com.kevin.sample;

 
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	 PieLayout step_graph;
	  int dpScale;
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 initDpScale();
		
		  findViewById(R.id.arc).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				setStepGraph(160, 160);
			}
		});
		  
		findViewById(R.id.hoof).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setHoofGraph(100);
			}
		});
		  //setHoofGraph(100 * dataBody.getCityTotalStepCount() / cityTotalStep);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	   /**
     * O그래프 데이터 셋팅
     */

    public void setStepGraph(int step, int goalstep) {
        boolean flag = Boolean.TRUE;
        if (step_graph != null) {
            // step_graph.getPieSkin().clear();
            // step_graph.invalidate();
            flag = Boolean.FALSE;
            step_graph.initArc();
        }
        step_graph = (PieLayout)findViewById(R.id.step_graph1);

        // View 초기화

        step_graph.setMaxAngle(-160, 160);
        step_graph.setSizeToScale(false);
        step_graph.setStrokSize(14);
        if (step_graph.getPieSkin() != null && step_graph.getPieSkin().size() < 1) {
            step_graph.addPieSkin(new PiePercentSkin(13, 20)); // 퍼센트
            step_graph.addPieSkin(new PieArcRoundSkin()); // 시작, 끝 캡
        }
        // step_graph.isBackArc(true); //뒤배경
        // step_graph.setBackArc(-160,320, Jwc.getColor("#39AED8")); //max
        if (goalstep != 0) {
            step_graph.addArc(step * 100 / goalstep, Color.WHITE, false);
        } else {
            step_graph.addArc(0, Color.WHITE, false);
        }
        // step_graph.invalidate();

        step_graph.animStart(0);
    }
    
    

    /**
     * N그래프 데이터 셋팅
     * 
     * @param percent
     */
    private void setHoofGraph(int percent) {
        HoofGraph hg = (HoofGraph) findViewById(R.id.hoofGraph);
        hg.setDpScale(this.dpScale);
        hg.setPercent(percent);
        hg.invalidate();

    }
    
    /**
     * Dp Scale 셋팅
     */
    private void initDpScale() {
        DisplayMetrics metrics = new DisplayMetrics();
      
        	MainActivity.this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        dpScale = metrics.densityDpi / 160;
    }
}
