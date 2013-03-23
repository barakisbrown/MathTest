package com.barakisbrown;

import java.util.Iterator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.barakisbrown.Quiz;
import android.util.Log;
import com.barakisbrown.ProblemHelper;

public class ProblemActivity extends Activity implements OnClickListener 
{
	private int numProblem = 0;
	private int maxProblem = 0;
	private int UserGuessed = 0;
	private int LeftSide;
	private int RightSide;
	private String problemLabelString;
	private String displayString;
	private Quiz quiz;
	private ProblemHelper helper;
	// Controls Here
	TextView problem;
	Button submit;
	ImageView firstLeft;
	ImageView secondLeft;
	ImageView firstRight;
	ImageView secondRight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.problem_layout);
		// I need to get the value from the bundle that MathActivity Sent Here
		// initialize my own variables here
		maxProblem = getIntent().getIntExtra("Problems",0);
		// DEBUG BEGIN
		Log.d("ProblemActivity","Quiz Has been initialized with " + maxProblem + " elements");
		Log.d("ProblemActivity onCreate()","Problems = " + maxProblem);
		// DEBUG END
		try 
		{
			quiz = Quiz.initQuiz(maxProblem);
			quiz.LoadQuiz();
			
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		problemLabelString = getResources().getString(R.string.ProblemLabel);
		helper = new ProblemHelper();
		// initialize form controls
		problem = (TextView)findViewById(R.id.problemLabel);
		firstLeft = (ImageView)findViewById(R.id.leftNum1);
		secondLeft = (ImageView)findViewById(R.id.leftNum2);
		firstRight = (ImageView)findViewById(R.id.rightNum1);
		secondRight = (ImageView)findViewById(R.id.rightNum2);
		submit = (Button)findViewById(R.id.SubmitProblem);
		submit.setOnClickListener(this);
		// now lets create the first problem dynamically
		displayLayout();
	}
	
	public void onClick(View v) 
	{
		// Debug Code Here
		Log.d("ProblemActivity onClick()","numProblem = " + numProblem);
		Log.d("ProblemActivity onClick()","maxProblem = " + maxProblem);
		// End Debug Code
		EditText result = (EditText)findViewById(R.id.guessTxtBox);
		try
		{
			UserGuessed = Integer.parseInt(result.getText().toString());
		} catch (java.lang.NumberFormatException numExcept)
		{
			return;
		}
		
		try {
			quiz.setGuess(UserGuessed, numProblem);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (numProblem  < (maxProblem - 1))
		{
			numProblem++;
			// clear current problem
			result.setText("");
			firstLeft.setImageResource(0);
			secondLeft.setImageResource(0);
			firstRight.setImageResource(0);
			secondRight.setImageResource(0);
			// ready to display next problem
			displayLayout();
		}
		else
		{
			quiz.determineScore();
			Intent data = new Intent();
			data.putExtra("Score",quiz.getScore());
			data.putExtra("Correct",quiz.getNumCorrect());
			data.putExtra("NumProblems",maxProblem);
			this.setResult(RESULT_OK, data);
			finish();
		}
	}

	private void displayLayout()
	{
		// Debug Code Here
		Log.d("ProblemActivity displayLayout()","numProblem = " + numProblem);
		Log.d("ProblemActivity displayLayout()","maxProblem = " + maxProblem);
		// End Debug Code
		LeftSide = quiz.getFirst(numProblem);
		RightSide = quiz.getSecond(numProblem);
		// DEBUG CODE HERE
		Log.d("ProblemActivity displayLayout()","LeftSide  = " + LeftSide);
		Log.d("ProblemActivity displayLayout()","RightSide = " + RightSide);
		// END DEBUG
		// update problem label
		displayString = String.format(problemLabelString, numProblem + 1,maxProblem);
		problem.setText(displayString);
		// display numbers
		helper.setLeftSide(LeftSide);
		helper.setRightSide(RightSide);
	    displayLeftSide();
	    displayRightSide();
	}

	  private void displayLeftSide()
	  {  
		  Iterator<Integer> itor = helper.buildLeftSide();
		  int size = helper.getSize();
		  if (size == 1)
		  {
			  int resId = itor.next();
			  itor.remove();
			  firstLeft.setImageResource(resId);
		  }
		  if (size == 2)
		  {
			  int resId_1 = itor.next();
			  itor.remove();
			  firstLeft.setImageResource(resId_1);
			  int resId_2 = itor.next();
			  itor.remove();
			  firstLeft.setImageResource(resId_2);
		  }
	  }
	  
	  private void displayRightSide()
	  {
		  Iterator<Integer> itor = helper.buildRightSide();
		  int size = helper.getSize();
		  if (size == 1)
		  {
			  int resId = itor.next();
			  itor.remove();
			  firstRight.setImageResource(resId);
		  }
		  if (size == 2)
		  {
			  int resId_1 = itor.next();
			  itor.remove();
			  firstRight.setImageResource(resId_1);
			  int resId_2 = itor.next();
			  itor.remove();
			  firstRight.setImageResource(resId_2);
		  }
	  }
}
