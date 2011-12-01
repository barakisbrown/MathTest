package com.barakisbrown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MathTestActivity extends Activity implements OnClickListener 
{
	private int PROBLEMCREATED = 0;
	private int currentProblemNumber = 0;
	private int LeftSide = 0;
	private int RightSide = 0;
	private Quiz quiz;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button beginButton = (Button)findViewById(R.id.beginTestButton);
        beginButton.setOnClickListener(this);
        quiz = Quiz.initQuiz();
    }

	public void onClick(View v) 
	{
		// This is where I will add the code to begin launching the quiz by calling ProblemActivity
		// number of times equal to the size of the quiz
		final int MAXQuizSize = quiz.getNumProblems();
		// Load Quiz data
		quiz.LoadQuiz();
		// Begin Quiz Loop
		
		LeftSide = quiz.getFirst(currentProblemNumber);
		RightSide = quiz.getSecond(currentProblemNumber);

		Bundle data = new Bundle();
		Intent intent = new Intent(this,ProblemActivity.class);
		data.putInt("ProblemNumber",currentProblemNumber++);
		data.putInt("MaxNumProblem",MAXQuizSize);
		data.putInt("LeftSide", LeftSide);
		data.putInt("RightSide",RightSide);
		intent.putExtras(data);
		this.startActivityForResult(intent,PROBLEMCREATED);
		Log.d("MathTestActivity OnClick()","Were here");
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode,Intent data)
	{
		Log.d("MathTestActivity", "Begin onActivityResult");
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode)
	    {
	     case 0:
	        	 if (resultCode == Activity.RESULT_OK)
	        	 {
	        		 Bundle res = data.getExtras();
	        		 int numProblem = res.getInt("ProblemNumber");
	        		 int userGuess = res.getInt("UserGuessed");
	        		 Log.d("Problem Number =",String.valueOf(numProblem));
	        		 Log.d("User Guessed   =",String.valueOf(userGuess));
	        		 quiz.setGuess(userGuess, currentProblemNumber);
	        	 }
	        	break;
	    } // end switch
		Log.d("MathTestActivity", "End onActivityResult");
	}	
}