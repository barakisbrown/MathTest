package com.barakisbrown;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.util.Log;

public class ProblemHelper 
{
	private List<Integer> display;
	private int LeftSide;
	private int RightSide;
	
	public ProblemHelper() 
	{ 
		LeftSide = RightSide = 0;
		display = new ArrayList<Integer>();
	}
	
	public Iterator<Integer> getList() 
	{
		return display.iterator();
	}

	public void setLeftSide(int leftSide) 
	{
		Log.d("ProblemHelper setLeftSide","leftSide = " + leftSide);
		LeftSide = leftSide;
	}

	public void setRightSide(int rightSide) 
	{
		Log.d("ProblemHelper setLeftSide","rightSide = " + rightSide);
		RightSide = rightSide;
	}
	
	public Iterator<Integer> buildLeftSide()
	{
		Log.d("ProblemHelper buildLeftSide","display size = " + display.size());
		setDisplay(LeftSide);
		Log.d("ProblemHelper buildLeftSide","display size = " + display.size());
		// Traverse the list so that i can see the elements to make sure it proper working before i return the iterator
		int value = 0;
		Iterator<Integer> itor = display.iterator();
		while (itor.hasNext())
		{
			value = itor.next();
			Log.d("ProblemHelper buildLeftSide","value = " + value);
			
		}
		
		
		return display.iterator();
	}
	
	public Iterator<Integer> buildRightSide()
	{
		Log.d("ProblemHelper buildRightSide","display size = " + display.size());
		setDisplay(RightSide);
		Log.d("ProblemHelper buildRightSide","display size = " + display.size());
		return display.iterator();
	}
	
	/***
	 * This is a generic builder which takes an integer and then returns the resources like the others too 
	 * in this class.
	 * @param intToImg
	 * @return Iterator<Int>
	 */
	public Iterator<Integer> builder(int intToImg)
	{
		setDisplay(intToImg);
		return display.iterator();
	}
	
	
	public int getSize()
	{
		return display.size();
	}
	
	private void setDisplay(int Number)
	{    
	    // add a bit of logic for the case where just the number '10' shows up. Instead of breaking it into two,
	    // I have created a new image for the number 10.
	    if (Number == 10)
	    {
	    	display.add(R.drawable.num10);
	    }
	    else
	    {
	    	String numStr = String.valueOf(Number);
		    Log.d("ProblemHelper setDisplay","Number = " + Number);
		    Log.d("ProblemHelper setDisplay","numStr = " + numStr);  
	    	
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
}
