package com.barakisbrown;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class LayoutTestingActivity extends Activity 
{
	/** Constants for this test */
	private static final int numProblems = 5;
	private static final int startProblem = 1;
	private static final int totalScored = 100;
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_testing);
        
        TextView problems = (TextView)this.findViewById(R.id.labelEdit1);
        String strProblems = getResources().getString(R.string.ProblemLabel);
        strProblems = String.format(strProblems, startProblem,numProblems);
        problems.setText(strProblems);
        
        TextView totalScore = (TextView)this.findViewById(R.id.finalScores);
        String strScores = getResources().getString(R.string.Total_Score_2);
        strScores = String.format(strScores, numProblems,numProblems,totalScored);
        totalScore.setText(strScores);
        
    }
}
