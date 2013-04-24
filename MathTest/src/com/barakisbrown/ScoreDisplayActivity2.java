package com.barakisbrown;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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
	
	@Override
	protected void onCreate(Bundle savedState) 
	{
		Log.d("ScoreDisplayActivity","Entering OnCreate()");
		super.onCreate(savedState);
		setContentView(R.layout.scoredisplay);
		
		final TextView totalScoreDisplay = (TextView)findViewById(R.id.finalScores);
		
		String totalDispStr = getResources().getString(R.string.Total_Score_2);
		
		totalScore = getIntent().getDoubleExtra("Score",100.0); 
		totalCorrect = getIntent().getIntExtra("Correct",5);
		numProblems = getIntent().getIntExtra("NumProblems",5);
		// manipulate totalscore where it will show 100% not .100%
		totalScore = totalScore * 100.00;
		totalDispStr = String.format(totalDispStr,numProblems,totalCorrect,totalScore);
		totalScoreDisplay.setText(totalDispStr);
		
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
