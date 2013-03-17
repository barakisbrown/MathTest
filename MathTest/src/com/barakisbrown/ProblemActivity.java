package com.barakisbrown;

import java.util.ArrayList;
import java.util.List;
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
	private List<Integer> display;
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
		display = new ArrayList<Integer>();
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
		// update problem label
		displayString = String.format(problemLabelString, numProblem + 1,maxProblem);
		problem.setText(displayString);
		// display numbers
	    displayLeftSide(LeftSide);
	    displayRightSide(RightSide);
	}

	  private void displayLeftSide(int lside)
	  {  
		  getDisplay(lside);
		  if (display.size() == 1)
		  {
		     int resID = display.remove(0);
		     firstLeft.setImageResource(resID);
		  }
		  else
		  {
		     int resID_1 = display.remove(0);
		     int resID_2 = display.remove(0);
		     firstLeft.setImageResource(resID_1);
		     secondLeft.setImageResource(resID_2);
		  }
		  display.clear();
	  }

	  private void displayRightSide(int rside)
	  {
		  getDisplay(rside);
		  if (display.size() == 1)
		  {
			  int resID = display.remove(0);
			  firstRight.setImageResource(resID);
		  }
		  else
		  {
			  int resID_1 = display.remove(0);
			  int resID_2 = display.remove(0);
			  firstRight.setImageResource(resID_1);
			  secondRight.setImageResource(resID_2);
		  }
		  display.clear();
	  }

	  private void getDisplay(int Number)
	  {
	  
	    String numStr = String.valueOf(Number);

	    int Length = numStr.length();

	    for (int counter = 0; counter < Length; counter++)
	    {
	      int displayNumber = Character.getNumericValue(numStr.charAt(counter));
	      switch (displayNumber)
	      {
	      case 0:
	    	  display.add(R.drawable.num0);
	    	  break;
	      case 1:
	    	  display.add(R.drawable.num1);
	    	  break;
	      case 2:
	    	  display.add(R.drawable.num2);
	    	  break;
	      case 3:
	    	  display.add(R.drawable.num3);
	    	  break;
	      case 4:
	    	  display.add(R.drawable.num4);
	    	  break;
	      case 5:
	    	  display.add(R.drawable.num5);
	    	  break;
	      case 6:
	    	  display.add(R.drawable.num6);
	    	  break;
	      case 7:
	    	  display.add(R.drawable.num7);
	    	  break;
	      case 8:
	    	  display.add(R.drawable.num8);
	    	  break;
	      case 9:
	    	  display.add(R.drawable.num9);
	    	  break;
	      default:
	    	  break;
	      }
	    }
	}
}
