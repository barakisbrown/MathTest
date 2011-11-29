package com.barakisbrown;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class TestProblemActivity extends Activity implements OnClickListener
{
  private static final int LeftSide = 5;
  private static final int RightSide = 27;
  private static final int totalSides = 32;

  public void onCreate(Bundle savedState)
  {
    super.onCreate(savedState);
    setContentView(R.layout.tstproblemactivity);
    displayLayout();

    Button submit = (Button)findViewById(R.id.SubmitProblem);
    submit.setOnClickListener(this);
  }

  private void displayLayout()
  {
    displayLeftSide();
    displayRightSide();
  }

  private void displayLeftSide()
  {
    List<Integer> display = getDisplay(LeftSide);
    if (display.size() == 1)
    {
      int resID = ((Integer)display.get(0)).intValue();
      ImageView firstNumber = (ImageView)findViewById(R.id.leftNum1);
      firstNumber.setImageResource(resID);
    }
    else
    {
      int resID_1 = ((Integer)display.get(0)).intValue();
      int resID_2 = ((Integer)display.get(1)).intValue();
      ImageView firstNumber = (ImageView)findViewById(R.id.leftNum1);
      ImageView secondNumber = (ImageView)findViewById(R.id.leftNum2);
      firstNumber.setImageResource(resID_1);
      secondNumber.setImageResource(resID_2);
    }
  }

  private void displayRightSide()
  {
    List<Integer> display = getDisplay(RightSide);
    if (display.size() == 1)
    {
      int resID = ((Integer)display.get(0)).intValue();
      ImageView firstNumber = (ImageView)findViewById(R.id.rightNum1);
      firstNumber.setImageResource(resID);
    }
    else
    {
      int resID_1 = ((Integer)display.get(0)).intValue();
      int resID_2 = ((Integer)display.get(1)).intValue();
      ImageView firstNumber = (ImageView)findViewById(R.id.rightNum1);
      ImageView secondNumber = (ImageView)findViewById(R.id.rightNum2);
      firstNumber.setImageResource(resID_1);
      secondNumber.setImageResource(resID_2);
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

  public void onClick(View arg0)
  {
    EditText result = (EditText)findViewById(R.id.guessTxtBox);
    int answer = Integer.parseInt(result.getText().toString());
    if (answer == totalSides)
    {
      Toast.makeText(this, "Congrats", 0).show();
    }
    else
    {
      String display = "Sorry..The answer is " + String.valueOf(totalSides);
      Toast.makeText(this, display, 0).show();
    }
  }
}