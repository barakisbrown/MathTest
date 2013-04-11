package com.barakisbrown;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.barakisbrown.ProblemHelper;

public class DynamicImageTest extends Activity implements OnClickListener
{
	private int leftSide = 100;
	private int rightSide = 100;
	private int total = 200;
	ProblemHelper helper;
	
	//GUI RELATED
	ImageView plusSign;
	ImageView equalSign;
	ImageView display;
	Button clickMe;
	View dynView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dynamic_image_test);
		helper = new ProblemHelper();
		display = (ImageView)findViewById(R.id.dynamic_image);
		clickMe = (Button)findViewById(R.id.begin_the_test);
		clickMe.setOnClickListener(this);	
	}

	public void onClick(View v) 
	{
		
	}
	
	
	
}
