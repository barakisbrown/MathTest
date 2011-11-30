package com.barakisbrown;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.barakisbrown.Quiz;

public class ScoreDisplayActivity extends Activity 
{
	private Quiz quiz = MathTestActivity.quiz;
	private int totalScore = 0;
	private int totalCorrect = 0;
	private int numProblems = 0;
	public void OnCreate(Bundle savedState)
	{
		Log.d("ScoreDisplayActivity","Entering OnCreate()");
		super.onCreate(savedState);
		setContentView(R.layout.scoredisplay);
		
		final TextView totalScoreDisplay = (TextView)findViewById(R.id.finalScoreLabel);
		
		String totalDispStr = totalScoreDisplay.getText().toString();
		
		totalScore = quiz.determineScore();
		totalCorrect = quiz.getNumCorrect();
		numProblems = quiz.getNumProblems();
		totalDispStr = String.format(totalDispStr,numProblems,totalCorrect,totalScore);
		totalScoreDisplay.setText(totalDispStr);
	}
}
