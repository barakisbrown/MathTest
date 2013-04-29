package com.barakisbrown;

import java.util.Iterator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.barakisbrown.ProblemHelper;

/***
 * This is the master ScoreDisplayActivity Class.
 * @author Matt
 *
 */
public class ScoreDisplayActivity2 extends Activity 
{
	private double totalScore = 0;
	private int totalCorrect = 0;
	private int numProblems = 0;
	private int backKeyPressedTimes = 0;
	private ProblemHelper helper;
	
	@Override
	protected void onCreate(Bundle savedState) 
	{
		Log.d("ScoreDisplayActivity","Entering OnCreate()");
		super.onCreate(savedState);
		setContentView(R.layout.scoredisplay_new);
		
		final ImageView probTotal = (ImageView)findViewById(R.id.probTotal);
		final ImageView probCorrect = (ImageView)findViewById(R.id.probCorrect);
		final LinearLayout probTotalScore = (LinearLayout)findViewById(R.id.probTotalScore);
		helper = new ProblemHelper();
		// get values from the intent that called this activity
		totalScore = getIntent().getDoubleExtra("Score",100.0); 
		totalCorrect = getIntent().getIntExtra("Correct",5);
		numProblems = getIntent().getIntExtra("NumProblems",5);
		// manipulate total score where it will show 100% not .100%
		totalScore = totalScore * 100.00;
		// NEW SECTION
		// For the following section I am going to be needing an iterator for displaying images to the screen
		Iterator<Integer> itor;
		// Display the number of Problems to the screen
		itor = helper.builder(numProblems);
		probTotal.setImageResource(itor.next());
		itor.remove();
		// I need to display the number for how many correct
		itor = helper.builder(totalCorrect);
		probCorrect.setImageResource(itor.next());
		itor.remove();
		// I need to display the user TotalScore to the Screen Here
		// Since this will be a variable number from 2 to 4 digits .. ie 2% to 100% .. I am using a view
		// then dynamically adding the numbers followed by the percent sign to it.
		ImageView iv = new ImageView(this);
		int dblvalue = (int)totalScore;
		itor = helper.builder(dblvalue);
		
		while(itor.hasNext())
		{
			iv.setScaleType(ScaleType.FIT_CENTER);
			iv.setImageResource(itor.next());
			// Might have to add a layout here
			probTotalScore.addView(iv);
			itor.remove();
		}
		// add percent sign
		iv.setScaleType(ScaleType.FIT_CENTER);
		iv.setImageResource(R.drawable.percent);
		probTotalScore.addView(iv);
		//
		// Code below will be the display of the problems missed.
		
		
		
		
	}
	@Override
	public void onBackPressed()
	{
		if (backKeyPressedTimes == 0)
		{
			String msg = "Press back key one more time to exit quiz.";
			Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
			backKeyPressedTimes++;
		}
		else if (backKeyPressedTimes == 1)
		{
			setResult(RESULT_OK);
			finish();
		}
	}
}
