package com.barakisbrown;

import java.util.Iterator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.barakisbrown.ProblemHelper;

public class DynamicImageTest extends Activity implements OnClickListener
{
	private int leftSide = 10;
	private int rightSide = 20;
	private int total = 30;
	ProblemHelper helper;
	
	//GUI RELATED
	LinearLayout dyn_view;
	ImageView plusSign;
	ImageView equalSign;
	Button clickMe;
	View dynView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dynamic_image_test);
		helper = new ProblemHelper();
		dyn_view = (LinearLayout)findViewById(R.id.dynList);
		clickMe = (Button)findViewById(R.id.begin_the_test);
		clickMe.setOnClickListener(this);	
	}

	public void onClick(View v) 
	{
		
		// plus sign
		plusSign = new ImageView(this);
		plusSign.setScaleType(ImageView.ScaleType.FIT_CENTER);
		plusSign.setImageResource(R.drawable.add);
		// equal sign
		equalSign = new ImageView(this);
		equalSign.setScaleType(ImageView.ScaleType.FIT_CENTER);
		equalSign.setImageResource(R.drawable.equalsign);
		// If there are elements, delete them and then dispaly what is needed.
		if (dyn_view.getChildCount() > 0)
		{
			dyn_view.removeAllViews();
			
		}
		// now i will be able to add the rest of those
		helper.setLeftSide(leftSide);
		helper.setRightSide(rightSide);
		// now lets build the leftside and iterate over it
		addToLayout(leftSide);
		// Now lets add the [+] sign
		dyn_view.addView(plusSign);
		// now lets add the right side
		addToLayout(rightSide);
		// now lets add the [=] sign
		dyn_view.addView(equalSign);
		// now lets add the total to it
		addToLayout(total);
	}
	
	private void addToLayout(int item)
	{
		ImageView iv;
		Iterator<Integer> itor = helper.builder(item);
		while (itor.hasNext())
		{
			iv = new ImageView(this);
			iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
			iv.setImageResource(itor.next());
			itor.remove();
			dyn_view.addView(iv);
		}
		
	}
	
	
}
