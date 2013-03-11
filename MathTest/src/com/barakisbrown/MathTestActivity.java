package com.barakisbrown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MathTestActivity extends Activity implements OnItemSelectedListener
{
	private static final int SCORE_IT = 1000;
	private ArrayAdapter<String> adapter;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Spinner choices = (Spinner)findViewById(R.id.choices);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.number_of_problems));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choices.setAdapter(adapter);
        choices.setGravity(Gravity.CENTER);
        choices.setOnItemSelectedListener((OnItemSelectedListener) this);
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
				// now lets call the scoredisplayactivity
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
	
	
}