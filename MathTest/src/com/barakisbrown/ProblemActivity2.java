package com.barakisbrown;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.File;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProblemActivity2 extends Activity implements OnClickListener 
{
	// GUI RELATED
	private ProblemHelper helper;
	private LinearLayout dynLayout;
	private ImageView plusSign;
	private ImageView equalSign;
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
		helper = new ProblemHelper();
		// Init GUI Controls
		// Setup Width/Height
		dynLayout = (LinearLayout)findViewById(R.id.problemLayout);
		dynLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		// Other UI Init
		problemNumberTxt = (TextView)findViewById(R.id.problemLabel);
		submit = (Button)findViewById(R.id.SubmitProblem);
		submit.setOnClickListener(this);
		// Setup Plus Sign and Equal Side
		plusSign = new ImageView(this);
		plusSign.setScaleType(ImageView.ScaleType.FIT_CENTER);
		plusSign.setImageResource(R.drawable.add);
		// equal sign
		equalSign = new ImageView(this);
		equalSign.setScaleType(ImageView.ScaleType.FIT_CENTER);
		equalSign.setImageResource(R.drawable.equalsign);
		// now lets create the first problem dynamically
		displayLayout();
	
	}

	private void displayLayout() 
	{
		leftSide = quiz.getFirst(numProblem);
		rightSide = quiz.getSecond(numProblem);
		// Lets display the problem number
		displayString = String.format(problemLabelString, numProblem + 1,maxProblem);
		problemNumberTxt.setText(displayString);
		// begin displaying problem
		TextView blank = new TextView(this);
		blank.setText("          ");
		// begin adding controls
		dynLayout.addView(blank);
		// LeftSide of Problem
		addToLayout(leftSide);
		// PlusSign
		dynLayout.addView(plusSign);
		// RightSide of Equation
		addToLayout(rightSide);
		// EqualSign
		dynLayout.addView(equalSign);
		// dispaly GuessBox
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
	
	private void addToLayout(int item)
	{
		ImageView iv;
		Iterator<Integer> itor = helper.builder(item);
		// loop thru iterator and display items
		while(itor.hasNext())
		{
			iv = new ImageView(this);
			iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
			iv.setImageResource(itor.next());
			itor.remove();
			dynLayout.addView(iv);	
		}
	}

    private void writeProblems()
    {
        ProblemBase []problems = quiz.getIncorrectProblems();
        int size = problems.length;
        try
        {
            File path = new File(Environment.getExternalStorageDirectory() + "/incorrect_problems");
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
