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

public class ProblemActivity extends Activity implements OnClickListener 
{
	private int numProblem = 0;
	private int maxProblem = 5;
	private int UserGuessed = 0;
	private int UserAnswered = 0;
	private int LeftSide;
	private int RightSide;
	private int ActualAnswer;
	private String problemLabelString;
	private String displayString;
	private Quiz quiz = Quiz.initQuiz();
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
		// initialize my own varaiables here
		quiz.LoadQuiz();
		problemLabelString = getResources().getString(R.string.ProblemLabel);
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
		EditText result = (EditText)findViewById(R.id.guessTxtBox);
		UserGuessed = Integer.parseInt(result.getText().toString());
		quiz.setGuess(UserGuessed, numProblem);
		
		if (numProblem < 4)
		{
			numProblem++;
			result.setText("");
			displayLayout();
		}
		else
		{
			Intent scoreIT = new Intent(ProblemActivity.this,ScoreDisplayActivity.class);
			
			quiz.determineScore();
			scoreIT.putExtra("Score",quiz.getScore());
			scoreIT.putExtra("Correct",quiz.getNumCorrect());
			scoreIT.putExtra("NumProblems",maxProblem);
			
			startActivity(scoreIT);
		}
		
		
		
		
	}

	private void displayLayout()
	{
		LeftSide = quiz.getFirst(numProblem);
		RightSide = quiz.getSecond(numProblem);
		ActualAnswer = LeftSide + RightSide;
		// update problem label
		displayString = String.format(problemLabelString, numProblem + 1,maxProblem);
		problem.setText(displayString);
		// display numbers
	    displayLeftSide();
	    displayRightSide();
	}

	  private void displayLeftSide()
	  {
	    display = getDisplay(LeftSide);
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
	  }

	  private void displayRightSide()
	  {
	    display = getDisplay(RightSide);
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
	  }

	  private List<Integer> getDisplay(int Number)
	  {
	    List<Integer> retList = new ArrayList<Integer>();
	    String numStr = String.valueOf(Number);

	    int Length = numStr.length();

	    for (int counter = 0; counter < Length; counter++)
	    {
	      int displayNumber = Character.getNumericValue(numStr.charAt(counter));
	      switch (displayNumber)
	      {
	      case 0:
	    	  retList.add(R.drawable.num0);
	    	  break;
	      case 1:
	    	  retList.add(R.drawable.num1);
	    	  break;
	      case 2:
	    	  retList.add(R.drawable.num2);
	    	  break;
	      case 3:
	    	  retList.add(R.drawable.num3);
	    	  break;
	      case 4:
	    	  retList.add(R.drawable.num4);
	    	  break;
	      case 5:
	    	  retList.add(R.drawable.num5);
	    	  break;
	      case 6:
	    	  retList.add(R.drawable.num6);
	    	  break;
	      case 7:
	    	  retList.add(R.drawable.num7);
	    	  break;
	      case 8:
	    	  retList.add(R.drawable.num8);
	    	  break;
	      case 9:
	    	  retList.add(R.drawable.num9);
	    	  break;
	      default:
	    	  break;
	      }
	    }

	    return retList;
	}
}
