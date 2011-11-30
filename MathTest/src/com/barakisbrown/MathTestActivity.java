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
		int currentProblemNumber = 0;
		// This is where I will add the code to begin launching the quiz by calling ProblemActivity
		// number of times equal to the size of the quiz
		final int MAXQuizSize = quiz.getNumProblems();
		// Load Quiz data
		quiz.LoadQuiz();
		// Begin Quiz Loop
		Bundle data = new Bundle();
		Intent intent = new Intent(this,ProblemActivity.class);
		data.putInt("ProblemNUmber",currentProblemNumber);
		data.putInt("MaxNumProblems",MAXQuizSize);
		data.putInt("LeftSide", quiz.getFirst(currentProblemNumber));
		data.putInt("RightSide",quiz.getSecond(currentProblemNumber));
		intent.putExtras(data);
		startActivityForResult(intent,Activity.RESULT_OK);
		
		
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		 super.onActivityResult(requestCode, resultCode, data);
	     switch(requestCode)
	     {
	     case Activity.RESULT_OK:
	        	 if (resultCode == RESULT_OK)
	        	 {
	        		 Bundle res = data.getExtras();
	        		 int numProblem = res.getInt("NumProblem");
	        		 int userGuess = res.getInt("UserGuessed");
	        		 quiz.setGuess(userGuess,numProblem);
	        	 }
	        	break;
	     } // end switch
	}
	
}