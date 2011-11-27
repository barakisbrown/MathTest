package com.barakisbrown;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MathTestActivity extends Activity implements OnClickListener 
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button beginButton = (Button)findViewById(R.id.beginTestButton);
        beginButton.setOnClickListener(this);
    }

	public void onClick(View v) 
	{
		Toast.makeText(this,"Not Impelemented Yet",Toast.LENGTH_LONG).show();
	}
}