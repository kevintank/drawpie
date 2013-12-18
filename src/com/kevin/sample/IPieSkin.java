package com.kevin.sample;

import android.graphics.Canvas;

public interface IPieSkin {
	public void init(PieLayout pieLayout);
	public void draw(PieLayout pieLayout, Canvas canvas, float startAngle, float maxAngle,float startRadians, float radians, int arcIndex);
	
}
