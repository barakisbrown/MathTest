package com.barakisbrown;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.barakisbrown.ProblemHelper;

/***
 * This is the master ScoreDisplayActivity Class.
 * @author Matt
 *
 */
public class ScoreDisplayActivity2 extends Activity 
{
	private double totalScore = 0;
	private int totalCorrect = 0;
	private int numProblems = 0;
    private int numIncorrect = 0;
	private int backKeyPressedTimes = 0;
	private ProblemHelper helper;
    ProblemBase []problems;
	
	@Override
	protected void onCreate(Bundle savedState) 
	{
		Log.d("ScoreDisplayActivity","Entering OnCreate()");
		super.onCreate(savedState);
		setContentView(R.layout.scoredisplay_new);
		
		final ImageView probTotal = (ImageView)findViewById(R.id.probTotal);
		final ImageView probCorrect = (ImageView)findViewById(R.id.probCorrect);
		LinearLayout probTotalScore = (LinearLayout)findViewById(R.id.probTotalScore);
		LinearLayout mainLayout = (LinearLayout)findViewById(R.id.main);
		helper = new ProblemHelper();
		// get values from the intent that called this activity
		totalScore = getIntent().getDoubleExtra("Score",100.0); 
		totalCorrect = getIntent().getIntExtra("Correct",5);
		numProblems = getIntent().getIntExtra("NumProblems",5);
        numIncorrect = getIntent().getIntExtra("NumIncorrect",0);
		// manipulate total score where it will show 100% not .100%
		totalScore = totalScore * 100.00;
		// NEW SECTION
		// For the following section I am going to be needing an iterator for displaying images to the screen
		Iterator<Integer> itor;
		// Display the number of Problems to the screen
		itor = helper.builder(numProblems);
		probTotal.setImageResource(itor.next());
		itor.remove();
		// I need to display the number for how many correct
		itor = helper.builder(totalCorrect);
		probCorrect.setImageResource(itor.next());
		itor.remove();
		// I need to display the user TotalScore to the Screen Here
		// Since this will be a variable number from 2 to 4 digits .. ie 2% to 100% .. I am using a view
		// then dynamically adding the numbers followed by the percent sign to it.
		ImageView iv;
		int dblvalue = (int)totalScore;
		itor = helper.builder(dblvalue);
		
		while(itor.hasNext())
		{
			iv = new ImageView(this);
			iv.setScaleType(ScaleType.FIT_CENTER);
			iv.setImageResource(itor.next());
			itor.remove();
			// Might have to add a layout here
			probTotalScore.addView(iv);
		}
		// add percent sign
		iv = new ImageView(this);
		iv.setScaleType(ScaleType.FIT_CENTER);
		iv.setImageResource(R.drawable.percent);
		probTotalScore.addView(iv);
		// WORKING
		if (numIncorrect > 0)
		{
            problems = new ProblemBase[numIncorrect];
			LayoutInflater factory = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			TableLayout myView = (TableLayout)factory.inflate(R.layout.table_test,null);
			mainLayout.addView(myView);
			//
			// Load the problems into memory
	        loadIncorrectProblems();
	        //
	        // Display them to the screen
	        displayWrongProblems(myView);
		}
    }
	@Override
	public void onBackPressed()
	{
		if (backKeyPressedTimes == 0)
		{
			String msg = "Press back key one more time to exit quiz.";
			Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
			backKeyPressedTimes++;
		}
		else if (backKeyPressedTimes == 1)
		{
			setResult(RESULT_OK);
			finish();
		}
	}

    private void loadIncorrectProblems()
    {
        File path = new File(Environment.getExternalStorageDirectory() + "/incorrect_problems");
        // working .. this will write all the incorrect problems from disk to memory
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            for (int loop = 0;loop < numIncorrect; loop++)
                problems[loop] = (ProblemBase)ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException cnf)
        {
            cnf.printStackTrace();
        }
    }
    
    private ImageView getProblemNumber(int prodNumber)
    {
    	ImageView iv = new ImageView(this);
    	Iterator<Integer> itor = helper.builder(prodNumber);
    	iv.setImageResource(itor.next());
    	itor.remove();
    	return iv;
    }
    
    private LinearLayout getProblem(int probNumber)
    {
    	LinearLayout rtnView = new LinearLayout(this);
    	ImageView iv;
    	ImageView plusSign;
    	ImageView equalSign;
    	// Setup Plus Sign and Equal Side
		plusSign = new ImageView(this);
		plusSign.setScaleType(ImageView.ScaleType.FIT_CENTER);
		plusSign.setImageResource(R.drawable.add);
		// equal sign
		equalSign = new ImageView(this);
		equalSign.setScaleType(ImageView.ScaleType.FIT_CENTER);
		equalSign.setImageResource(R.drawable.equalsign);
		// now lets create the first problem dynamically
    	
    	Iterator<Integer> itor = helper.builder(problems[probNumber].getLeftSide());
    	rtnView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    	// left side of equation
    	while(itor.hasNext())
    	{
    		iv = new ImageView(this);
    		iv.setImageResource(itor.next());
    		iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
    		itor.remove();
    		rtnView.addView(iv);
    	}
    	// add plus sign
    	rtnView.addView(plusSign);
    	// right side
    	itor = helper.builder(problems[probNumber].getRightSide());
    	while(itor.hasNext())
    	{
    		iv = new ImageView(this);
    		iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
    		iv.setImageResource(itor.next());
    		itor.remove();
    		rtnView.addView(iv);
    	}
    	// equal sign
    	rtnView.addView(equalSign);
    	// return the view
    	return rtnView;
    }
    
    private LinearLayout getActualAnswer(int probNumber)
    {
    	LinearLayout rtnView = new LinearLayout(this);
    	ImageView iv;
    	// build display
    	Iterator<Integer> itor = helper.builder(problems[probNumber].getTotal());
    	rtnView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    	while(itor.hasNext())
    	{
    		iv = new ImageView(this);
    		iv.setImageResource(itor.next());
    		iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
    		itor.remove();
    		rtnView.addView(iv);
    	}
    	return rtnView;
    }
    
    /**
     * Display the wrong problems to the screen. It will do the following:
     * LOOP
     *  Display Problem Number Missed
     *  Display Actual Problem to the screen
     *  Display Actual Answer to the problem
     * END LOOP
     */
    private void displayWrongProblems(TableLayout grid)
    {
       
    }
}
