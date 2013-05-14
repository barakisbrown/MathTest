package com.barakisbrown;

import java.util.Random;
import android.util.Log;
import com.barakisbrown.ProblemBase;
/**
 * Quiz is to represent the 'business' login of the MathTest Application
 * I probably could turn this more into a generic version instead of hard coding
 * because all I would need to do is make my array dynamic instead of static like 
 * they are now present in this class.
 * 
 * I also wanted to do a singleton like this so that I can keep my memory low especially
 * since this will be used inside the android operating system where memory is premium
 * and something that has to be always considered.
 * 
 * NOTE: SpinnerTest Version
 * Since it is now no longer using a hard coded number, I made changes where it can use 
 * an number to make the size of the array to be used.
 * 
 * @author barakis mailto:barakis@barakisbrown.com
 * @version 1.0
 *
 */
public class Quiz
{
	private Random rnd = null;
	private static Quiz rtnObject = null;
	private int MAXPROBLEMS = 0;
	private static final int MAXNUMBERUSED = 99;
	private int numCorrect = 0;
	private int quizUsedCount = 1;
	private double score = 0.0;
	
	private ProblemBase []Problems;
	private int numIncorrect = 0;
	
	
	
	/***
	 * Private Construction for the Singleton. Since this will not longer be hard coded, this will now
	 * create an array based on how many problems the user has entered in the calling function.
	 * @param numProblems
	 */
	private Quiz(int numProblems)
	{
		if (rnd == null)
		{
			rnd = new Random();
			initVars(numProblems);
		}
	}
	
	private void initVars(int numProblems)
	{
		MAXPROBLEMS = numProblems;
		Problems = new ProblemBase[MAXPROBLEMS];
	}
	

	/**
	 * If Quiz static object has not been create it then call private constructor which will then
	 * create the random object generator.  If Quiz object has been created then simply return it
	 * back to the calling function. 
	 * I am adding a check to make sure I initialize this class properly.
	 * @return com.barakisbrown.Quiz
	 * @param numProblems The number of Problems that the Quiz will be based on [1..10]
	 */
	static public Quiz initQuiz(int numProblems)
	{
		if (numProblems < 1)
			numProblems = 1;
		else if (numProblems > 10)
			numProblems = 10;
		if (rtnObject == null)
		{
			rtnObject = new Quiz(numProblems);
			return rtnObject;
		}
		else
		{
			rtnObject = null;
			rtnObject = new Quiz(numProblems);
			return rtnObject;
		}
	}
	

	// I could do some type checking below in this set of getters. Might look into it later.
	public int getQuizNumber()     { return quizUsedCount; }
	public int getFirst(int index) 
	{
		// BEGIN DEBUG
		Log.d("Quiz getFirst()","Index = " + index);
		Log.d("Quiz getFirst()","MAXPROBLEMS = " + MAXPROBLEMS);
		// END DEBUG
		return Problems[0].getLeftSide(); 
	}
	public int getSecond(int index) { return Problems[index].getRightSide(); }
	public int getAnswer(int index) { return Problems[index].getTotal(); }
	public int getNumProblems()     { return MAXPROBLEMS; }
	public int getNumCorrect()      { return numCorrect; }
	public double getScore()        { return score; }
	
	
	/**
	 * Sets Guess[index] to answer
	 * @param Guess answer that the user gave
	 * @param index where in the Guess array I want to store this result
	 * @throws Exception 
	 */
	public void setGuess(int Guess,int index) throws Exception
	{
		if (index < 0 || index > MAXPROBLEMS)
		{
			throw new Exception("Wrong Index Value .. Value is out of range from 0 .. MAXPROBLEMS");
		}
		else
		{
			Problems[index].setGuess(Guess);
		}
			
	}
	
	/**
	 * Checks the score based on how well the user did by his guesses compared to the answers
	 * For the caller to get the score he will need to use the getter function <code>double getScore()</code>
	 * @return double The score of the quiz in question
	 * 
	 */
	public double determineScore()
	{
		int numRight = 0;
		
		for (int index = 0;index < MAXPROBLEMS; index++)
		{
			if (Problems[index].isCorrect() == true)
				numRight++;
			else
				numIncorrect++;
		}
		score = (double)numRight / MAXPROBLEMS;
		numCorrect = numRight;
		return score;
	}
	
	/**
	 * Loads Problems and Answers Array so that it can be used.
	 * Note : This function needs to be called after initQuiz 
	 * @throws Exception 
	 */
	public void LoadQuiz() throws Exception
	{
		if (rnd == null)
		{
			// Need to either return an exception stating that Quiz has not be initialized yet
			throw new Exception("Quiz needs to be initialized first. Make sure you have called Quiz.Init(numProblems) First!");
		}
		// since rnd variable exists then I will need to create the loop that makes the quiz
		for (int X = 0;X < MAXPROBLEMS; X++)
		{
			int first = rnd.nextInt(MAXNUMBERUSED);
			int second = rnd.nextInt(MAXNUMBERUSED);
			int total = first + second;
			Problems[X] = new ProblemBase(first,second,total);
		}
		listProblems();
		quizUsedCount++;
	}
	
	/**
	 * return the problem class so that I can use it to display to the screen
	 */
	ProblemBase[] getAllProblems()
	{
		return Problems;
	}
	
	ProblemBase[] getIncorrectProblems()
	{
		int idx = 0;
		ProblemBase []Incorrect = new ProblemBase[numIncorrect];
		for (int loop = 0;loop < MAXPROBLEMS; loop++)
		{
			if (Problems[loop].isCorrect() == false)
			{
				Incorrect[idx++] = Problems[loop];
				
			}				
		}
		return Incorrect;
	}
	
	private void listProblems()
	{
		int lside = 0,rside = 0,tot = 0;
		int numProb = MAXPROBLEMS;
		
		for (int loop = 0;loop < numProb; loop++)
		{
			lside = Problems[loop].getLeftSide();
			rside = Problems[loop].getRightSide();
			tot = Problems[loop].getTotal();
			// display to the log file from these numbers
			Log.d("listProblems()","Problem #" + loop + 1);
			Log.d("listProblems()","LeftSide =" + lside);
			Log.d("listProblems()","RightSide =" + rside);
			Log.d("listProblems()","Total =" + tot);
		}
	}
}
