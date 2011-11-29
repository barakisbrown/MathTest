package com.barakisbrown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MathTestActivity extends Activity implements OnClickListener 
{
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
		Quiz quiz = Quiz.initQuiz();
		// This is where I will add the code to begin launching the quiz by calling ProblemActivity
		// number of times equal to the size of the quiz
		final int MAXQuizSize = quiz.getNumProblems();
		// Load Quiz data
		quiz.LoadQuiz();
		// Begin Quiz Loop
		
		for (int currentProblemNumber = 1;currentProblemNumber < MAXQuizSize;currentProblemNumber++)
		{
			Bundle dataStore = new Bundle();
			dataStore.putInt("currentProblemNumber", currentProblemNumber);
			dataStore.putInt("MaxNumQuestions",MAXQuizSize);
			dataStore.putParcelable("QUIZ",(Parcelable) quiz);
			// Start Again After This
		}
		
	}
}