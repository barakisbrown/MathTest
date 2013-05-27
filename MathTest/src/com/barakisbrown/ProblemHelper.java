package com.barakisbrown;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.util.Log;

public class ProblemHelper 
{
	private List<Integer> display;
	
	public ProblemHelper() 
	{ 
		display = new ArrayList<Integer>();
	}
	
	public Iterator<Integer> getList() 
	{
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
	
	/**
	 * Return the size of the Collection[display]
	 * @return integer size of the collection
	 */
	public int getSize()
	{
		return display.size();
	}
	
	/**
	 * This function will take a number and then convert each of the digits into their Image Version and then
	 * store them in the collection called display which then can be iterated over and be displayed.
	 * For Example : setDisplay(10) would take the number of 10 and break them into the digits '1' and '0'
	 *             : and then it will take 1 and create an ImageResourceId and store it.
	 *             : continues until all of the digits are completed
	 * @param Number A number that will be translated to Images of the Same Number stored in collection display
	 */
	private void setDisplay(int Number)
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
