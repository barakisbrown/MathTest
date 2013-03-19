package com.barakisbrown;

import java.util.ArrayList;
import java.util.List;

public class Utils 
{
	private List<Integer> display;
	
	public Utils()
	{
		display = new ArrayList<Integer>();
	}
	
	List<Integer> getDisplay(int Number)
	{
		displayProblem(Number);
		return display;
	}
	
	public void displayProblem(int Number)
	  {
	  
	    String numStr = String.valueOf(Number);

	    int Length = numStr.length();

	    for (int counter = 0; counter < Length; counter++)
	    {
	      int displayNumber = Character.getNumericValue(numStr.charAt(counter));
	      switch (displayNumber)
	      {
	      case 0:
	    	  display.add(R.drawable.num0);
	    	  break;
	      case 1:
	    	  display.add(R.drawable.num1);
	    	  break;
	      case 2:
	    	  display.add(R.drawable.num2);
	    	  break;
	      case 3:
	    	  display.add(R.drawable.num3);
	    	  break;
	      case 4:
	    	  display.add(R.drawable.num4);
	    	  break;
	      case 5:
	    	  display.add(R.drawable.num5);
	    	  break;
	      case 6:
	    	  display.add(R.drawable.num6);
	    	  break;
	      case 7:
	    	  display.add(R.drawable.num7);
	    	  break;
	      case 8:
	    	  display.add(R.drawable.num8);
	    	  break;
	      case 9:
	    	  display.add(R.drawable.num9);
	    	  break;
	      default:
	    	  break;
	      }
	    }
	}
}
