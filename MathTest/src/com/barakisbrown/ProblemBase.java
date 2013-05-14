package com.barakisbrown;

import android.util.Log;
/***
 * Instead of storing them as arrays, I will store them in a class. For this application, regardless of the operand, I will always
 * be using two numbers equaling a value. ie. 6 + 4 = 10, 6 * 4 = 24, 6 - 4 = 2, etc.
 * @author barakis
 *
 */

public class ProblemBase 
{

	private int leftSide, rightSide;
	private int total;
	private int guess;
	private boolean correct;
	
	public ProblemBase()
	{
		leftSide = rightSide = total = 0;
		guess = 0;
		correct = true;
	}
	
	public ProblemBase(int left,int right)
	{
		leftSide = left;
		rightSide = right;
		total = leftSide + rightSide;
		guess = 0;
		correct = false;
	}
	
	// get section
	public int getLeftSide() { return leftSide; }
	public int getRightSide() { return rightSide; }
	public int getTotal() { return total; }
	public int getGuess() { return guess; }
	public boolean isCorrect() { return correct; }
	
	//set section
	public void setGuess(int userGuessed) 
	{
		guess = userGuessed;
		correct = (guess == total) ? true : false;		
	}
	
	public void setLeftSide(int left)
	{
		leftSide = left;
	}
	
	public void setRightSide(int right)
	{
		rightSide = right;
	}
	
	public void setTotal(int value)
	{
		total = value;
	}
}
