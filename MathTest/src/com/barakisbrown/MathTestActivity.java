package com.barakisbrown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	
	
}