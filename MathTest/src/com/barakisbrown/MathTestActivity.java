package com.barakisbrown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MathTestActivity extends Activity implements OnClickListener 
{
	public static Quiz quiz = Quiz.initQuiz();
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button beginButton = (Button)findViewById(R.id.beginTestButton);
        beginButton.setOnClickListener(this);
    }

	public void onClick(View v) 
	{
		// This is where I will add the code to begin launching the quiz by calling ProblemActivity
		// number of times equal to the size of the quiz
		final int MAXQuizSize = quiz.getNumProblems();
		// Load Quiz data
		quiz.LoadQuiz();
		// Begin Quiz Loop
		
		
		for (int currentProblemNumber = 1;currentProblemNumber < MAXQuizSize;currentProblemNumber++)
		{
			Bundle dataStore = new Bundle();
			dataStore.putInt("ProblemNumber", currentProblemNumber);
			dataStore.putInt("MaxNumProblems",MAXQuizSize);
			// Start Again After This
			Intent intent = new Intent(this,ProblemActivity.class);
			startActivity(intent);	
		}
		// now we need to display the score page
		Intent scoreIntent = new Intent(this,ScoreDisplayActivity.class);
		startActivity(scoreIntent);
	}
}