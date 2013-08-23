package com.barakisbrown;

import android.util.Log;

import java.io.Serializable;

/***
 * Instead of storing them as arrays, I will store them in a class. For this application, regardless of the operand, I will always
 * be using two numbers equaling a value. ie. 6 + 4 = 10, 6 * 4 = 24, 6 - 4 = 2, etc.
 * @author barakis
 *
 */

public class ProblemBase implements Serializable
{
    private int leftSide, rightSide;
    private int total;
    private int guess;
    private boolean correct;
    private static final long serialVersionUID = 46543445;
    
    public ProblemBase()
    {
        leftSide = rightSide = total = 0;
        guess = 0;
        correct = true;
    }
    
    public ProblemBase(int left,int right,int totalValue)
    {
        leftSide = left;
        rightSide = right;
        total = totalValue;
        guess = 0;
        correct = false;
    }
    
    // get section
    public int getLeftSide() { return leftSide; }
    public int getRightSide() { return rightSide; }
    public int getTotal() { return total; }
    public int getGuess() { return guess; }
    public boolean isCorrect() { return correct; }
    
    /**
     * setter for what the user guessed
     * @param userGuessed Integer
     */
    public void setGuess(int userGuessed) 
    {
        guess = userGuessed;
        correct = (guess == total);
    }
    
    /**
     * setter for what number is on the left side of the equation
     * @param left Integer
     */
    public void setLeftSide(int left)
    {
        leftSide = left;
    }
    
    /**
     * setter for what number is on the right side of the equation
     * @param right Integer
    */
    public void setRightSide(int right)
    {
        rightSide = right;
    }
    
    /**
     * setter for the total value
     * @param value Integer
    */
    public void setTotal(int value)
    {
        total = value;
    }
    
    /**
     * DEBUG METHOD 
     * Takes all the variables and then displays them out to the log
    */
    public void display()
    {
        Log.d("Problem Base","LeftSide  = " + leftSide);
        Log.d("Problem Base","RightSide = " + rightSide);
        Log.d("Problem Base","Total     = " + total);
        Log.d("Problem Base","Guess     = " + guess);
        Log.d("Problem Base","Correct   = " + correct);
    }
}
