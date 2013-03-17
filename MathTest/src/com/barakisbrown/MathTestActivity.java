package com.barakisbrown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/***
 * This is the master MathTestActivity Class.
 * @author Matt
 *
 */
public class MathTestActivity extends Activity implements OnClickListener
{
	private static final int SCORE_IT = 1000;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button begin = (Button)findViewById(R.id.StartQuiz);
        begin.setOnClickListener(this);
     
    }
    
   	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode)
		{
		case SCORE_IT:
		{
			if (resultCode == Activity.RESULT_OK)
			{
				
				double score = data.getDoubleExtra("Score", 0.0);
				int correct = data.getIntExtra("Correct",0);
				int numProblem = data.getIntExtra("NumProblems",0);
				// now lets call the Activity ScoreDisplayActivity
				Intent scoreIT = new Intent(this,ScoreDisplayActivity.class);
	
				scoreIT.putExtra("Score",score);
				scoreIT.putExtra("Correct",correct);
				scoreIT.putExtra("NumProblems",numProblem);
				
				startActivity(scoreIT);
			}
		}
		default:
			break;
		}
	}



	public void onClick(View arg0) 
	{
		Intent IT = new Intent(this,ProblemActivity.class);
		this.startActivityForResult(IT,SCORE_IT);
		
	}	
	
	
=======
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/***
 * I have modified this from the original where I will use a spinner instead of button. The reason is that when I used
 * a button it was hard coded number of problems but now I want to make it where I can chose how many problems.
 * 
 * @author barakis
 *
 */
public class MathTestActivity extends Activity implements OnItemSelectedListener
{
	private static final int SCORE_IT = 1000;
	private static final int SCORE_IT_FINISH = 1001;
	private ArrayAdapter<String> adapter;
	private Spinner choices;
	
	/***
	 * This will initialize the whole program since this is the launching point.
	 * @return savedInstanceState -- allows you to pass information between activities.
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        choices = (Spinner)findViewById(R.id.choices);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.number_of_problems));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choices.setAdapter(adapter);
        choices.setGravity(Gravity.CENTER);
        choices.setOnItemSelectedListener((OnItemSelectedListener) this);
     
    }
    
    /***
     * When this called back, once ProblemActivity is finished, it will then send information
     * to ScoreDisplayActivity to display it to the screen.
     * 
     */
   	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode)
		{
		case SCORE_IT:
		{
			if (resultCode == Activity.RESULT_OK)
			{
				
				double score = data.getDoubleExtra("Score", 0.0);
				int correct = data.getIntExtra("Correct",0);
				int numProblem = data.getIntExtra("NumProblems",0);
				// now lets call the Activity ScoreDisplayActivity
				Intent scoreIT = new Intent(this,ScoreDisplayActivity.class);
				// store information which ScoreDisplayActivity will need to use.
				scoreIT.putExtra("Score",score);
				scoreIT.putExtra("Correct",correct);
				scoreIT.putExtra("NumProblems",numProblem);
				
				startActivityForResult(scoreIT,SCORE_IT_FINISH);
				Log.d("onActivityResults","Do I see this?");
			}
		}
		case SCORE_IT_FINISH:
		{
			if (resultCode == Activity.RESULT_OK)
			{
				choices.setSelection(0);
			}
		}
		default:
			break;
		}
	}
   	
   	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long arg3) 
	{
		Log.d("OnItemSelected Function","position = " + position);
	
		if (position == 0)
		{	
			return;
		}
		else
		{
			Intent IT = new Intent(this,ProblemActivity.class);
			IT.putExtra("Problems",position);
			this.startActivityForResult(IT,SCORE_IT);
		}
	}


   	public void onNothingSelected(AdapterView<?> arg0){ }

>>>>>>> refs/remotes/origin/spinner_test
}
