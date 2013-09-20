package com.barakisbrown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ProblemActivity2 extends Activity implements OnClickListener 
{
	// GUI RELATED
	private ProblemBuilder helper;
	private LinearLayout dynLayout;
	private EditText guessBox;
	private Button submit;
	private TextView problemNumberTxt;
	// NON GUI RELATED
	private Quiz quiz;
	private int leftSide = 0, rightSide = 0;
	private int numProblem = 0;
	private int maxProblem = 0;
	private int UserGuessed = 0;
	private String problemLabelString;
	private String displayString;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.problem_layout_new);
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
		// Init GUI Controls
		helper = new ProblemBuilder(this);
		// Setup Width/Height
		dynLayout = (LinearLayout)findViewById(R.id.problemLayout);
		dynLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		// Other UI Init
		problemNumberTxt = (TextView)findViewById(R.id.problemLabel);
		submit = (Button)findViewById(R.id.SubmitProblem);
		submit.setOnClickListener(this);
		// now lets create the first problem dynamically
		displayLayout();
	
	}

	private void displayLayout() 
	{
		leftSide = quiz.getFirst(numProblem);
		rightSide = quiz.getSecond(numProblem);
	    // Modification here with my new class
		helper.setLeftSide(leftSide);
		helper.setRightSide(rightSide);
		// Lets display the problem number
		displayString = String.format(problemLabelString, numProblem + 1,maxProblem);
		problemNumberTxt.setText(displayString);
		// Section needs work is here
		dynLayout = helper.buildProblem(dynLayout);
		// display GuessBox
		guessBox = new EditText(this);
		guessBox.setInputType(InputType.TYPE_CLASS_NUMBER);
		guessBox.requestFocus();
		dynLayout.addView(guessBox);
		
	}

	public void onClick(View arg0) 
	{
		try
		{
			UserGuessed = Integer.parseInt(guessBox.getText().toString());
		}catch(java.lang.NumberFormatException numExcept)
		{
			return;
		}catch(NullPointerException nullExcept)
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
			guessBox.setText("");
			// clear dynView
			dynLayout.removeAllViews();
			// ready to display next problem
			displayLayout();
		}
		else
		{
			quiz.determineScore();
            writeProblems();
			Intent data = new Intent();
			data.putExtra("Score",quiz.getScore());
			data.putExtra("Correct",quiz.getNumCorrect());
			data.putExtra("NumProblems",maxProblem);
            data.putExtra("NumIncorrect",quiz.getIncorrectProblems().length);
			this.setResult(RESULT_OK, data);
			finish();
		}
	}
	
    private void writeProblems()
    {
        ProblemBase []problems = quiz.getIncorrectProblems();
        int size = problems.length;
        try
        {
        	String location = Environment.getExternalStorageDirectory() + "/incorrect_problems";
            File path = new File(location);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            for (int loop = 0;loop < size; loop++)
                oos.writeObject(problems[loop]);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            Log.v("ProblemActivity2","Serialization Error "+e.getMessage());
            e.printStackTrace();
        }
    }

}
