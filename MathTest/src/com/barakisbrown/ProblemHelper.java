package com.barakisbrown;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ProblemHelper helps display a math problem to the screen. 
 * It assumes that all problems follow the same format: X + Y = Z
 * Where X,Y,Z are integers with the following conditions:
 * 0 < X <= 99  
 * 0 < Y <= 99
 * 0 < Z <= 198
 * 
 * All Numbers have a image that represents that ranging from 0 to 10.
 * I also have the following operands : + AND =
 * 
 * @author barakis
 *
 */
public class ProblemHelper 
{
    private List<Integer> display;
    protected int LeftSide;
    protected int RightSide;
    
    public ProblemHelper() 
    { 
        LeftSide = RightSide = 0;
        display = new ArrayList<Integer>();
    }
    
    /**
     * returns the iterator in cases I need to traverse it.
     * this function might be removed later.
     * @return Iterator<Integer>
    */
    public Iterator<Integer> getList() 
    {
        return display.iterator();
    }
    
    /**
     * returns the size of the Array
     * @return integer
    */
    public int getSize()
    {
        return display.size();
    }
    
    /**
     * setter for LeftSide
     * param leftSide Integer
    */
    public void setLeftSide(int leftSide) 
    {
        LeftSide = leftSide;
    }
    
    /**
     * setter for RightSide
     * @param rightSide
    */
    public void setRightSide(int rightSide) 
    {
        RightSide = rightSide;
    }
    
    /**
     * stores in the Array the Image resource of LeftSide
     * 
     * @return Iterator<Integer>
    */
    public Iterator<Integer> buildLeftSide()
    {
        setDisplay(LeftSide);       
        return display.iterator();
    }
    
    /**
     * stores in the Array the Image resource of LeftSide
     * 
     * @return Iterator<Integer>
    */
    public Iterator<Integer> buildRightSide()
    {
        setDisplay(RightSide);
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
     * Takes a number and stores the resource of the image in the array
     * which then can be returned.
     * 
     * @param Number number to be converted to a image
     */
    private void setDisplay(int Number)
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
          case 10:
              display.add(R.drawable.num10);
          default:
              break;
          }
        }
    }   
}
