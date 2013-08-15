
package com.barakisbrown;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.barakisbrown.ProblemHelper;

import java.util.Iterator;

public class TableTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_test_new);
        TableLayout tl = (TableLayout)findViewById(R.id.tableID2);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        Log.d("TableTestActivity","Number of Children = " + tl.getChildCount());
        // add stuff
        TextView tvLeft = new TextView(this);
        tvLeft.setLayoutParams(lp);
        tvLeft.setBackgroundColor(Color.WHITE);
        tvLeft.setGravity(Gravity.LEFT);
        tvLeft.setText("OMG");
        TextView tvCenter = new TextView(this);
        tvCenter.setLayoutParams(lp);
        tvCenter.setBackgroundColor(Color.BLUE);
        tvCenter.setGravity(Gravity.CENTER);
        tvCenter.setText("IT");
        TextView tvRight = new TextView(this);
        tvRight.setLayoutParams(lp);
        tvRight.setBackgroundColor(Color.RED);
        tvRight.setGravity(Gravity.RIGHT);
        tvRight.setText("WORKS");
         
        // Create a new row and lets add it to the table
        TableRow row = new TableRow(this);
        TableRow row1 = new TableRow(this);
        TableRow row2 = new TableRow(this);
        row.addView(tvLeft);
        row1.addView(tvCenter);
        row2.addView(tvRight);
        // add views
        tl.addView(row);
        tl.addView(row1);
        tl.addView(row2);
        Log.d("TableTestActivity","Number of Children = " + tl.getChildCount());
        tl.addView(testDisplay());
        Log.d("TableTestActivity","Number of Children = " + tl.getChildCount());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.table_test, menu);
        return true;
    }
    
    LinearLayout testDisplay()
    {
        TableRow.LayoutParams rowlp = new TableRow.LayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        LinearLayout rtnView = new LinearLayout(this);
        ProblemHelper helper = new ProblemHelper();
        
        int numberToDisplay = 1000;
        
        ImageView iv;
        Iterator<Integer> itor = helper.builder(numberToDisplay);
        // set Layout for the LinearLayout Here
        rtnView.setLayoutParams(rowlp);
        // loop thru iterator and display items
        while(itor.hasNext())
        {
            iv = new ImageView(this);
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            iv.setImageResource(itor.next());
            itor.remove();
            rtnView.addView(iv);  
        }
        return rtnView;
    }
    
    
    
    
    
    
    
}
