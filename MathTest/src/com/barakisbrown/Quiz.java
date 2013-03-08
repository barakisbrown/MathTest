package com.barakisbrown;

import java.util.Random;

/**
 * Quiz is to represent the 'business' login of the MathTest Application
 * I probably could turn this more into a generic version instead of hardcoding
 * because all I would need to do is make my array dynamic instead of static like 
 * they are now present in this class.
 * 
 * I also wanted to do a singleton like this so that I can keep my memory low especially
 * since this will be used inside the android operating system where memory is premium
 * and something that has to be always considered.
 * 
 * @author barakis mailto:barakis@barakisbrown.com
 * @version 1.0
 *
 */
public class Quiz
{
	private Random rnd = null;
	private static Quiz rtnObject = null;
	private final int MAXPROBLEMS = 5;
	private static final int MAXNUMBERUSED = 21;
	private int numCorrect = 0;
	
	private int []Answers;
	private int []Guesses;
	private int[][] Problems;
	
	private int quizUsedCount = 0;
	

	private Quiz()
	{
		if (rnd == null)
		{
			rnd = new Random();
			Problems = new int[5][2];
			Answers = new int[MAXPROBLEMS];
			Guesses = new int[MAXPROBLEMS];
		}
	}
	

	/**
	 * If Quiz static object has not been create it then call private constructor which will then
	 * create the random object generator.  If Quiz object has been created then simply return it
	 * back to the calling function.
	 * @return com.barakisbrown.Quiz
	 */
	static public Quiz initQuiz()
	{
		if (rtnObject == null)
		{
			rtnObject = new Quiz();
			return rtnObject;
		}
		return rtnObject;
	}
	
	public int getQuizNumber()     { return quizUsedCount + 1; }
	public int getFirst(int index) { return Problems[index][0]; }
	public int getSecond(int index) { return Problems[index][1]; }
	public int getAnswer(int index) {  return Answers[index]; }
	public int getNumProblems()     { return this.MAXPROBLEMS; }
	public int getNumCorrect()      { return numCorrect; }
	public double getScore()        { return ((double)numCorrect)/MAXPROBLEMS; }
	
	/**
	 * Checks the Answers Array vs the Guess Array at the index to determine if the
	 * Guess is correct or not
	 * @param index
	 * @return true Guess = Answer, false otherwise
	 */
	private boolean isCorrect(int index)
	{
		if (index < 0 || index > MAXPROBLEMS)
		{
			throws new exception("Wrong Index Value");
		}
		if (Answers[index] == Guesses[index])
			return true;
		else
			return false;
	}
	
	/**
	 * Sets Guess[index] to answer
	 * @param Guess answer that the user gave
	 * @param index int where in the Guess array I want to store this result
	 */
	public void setGuess(int Guess,int index)
	{
		if (index < 0 || index > MAXPROBLEMS)
		{
			throws new exception("Wrong Index Value");
		}
		Guesses[index] = Guess;
	}
	
	/**
	 * Checks the score based on how well the user did by his guesses compared to the answers
	 * For the caller to get the score he will need to use the getter function <code>double getScore()</code>
	 * 
	 */
	public void determineScore()
	{
		int numRight = 0;
		
		for (int index = 0;index < MAXPROBLEMS; index++)
		{
			if (isCorrect(index))
				numRight++;
		}
		
		numCorrect = numRight;
	}
	
	/**
	 * Loads Problems and Answers Array so that it can be used.
	 * Note : This function needs to be called after initQuiz 
	 */
	public void LoadQuiz()
	{
		if (rnd == null)
		{
			// Need to either return an exception stating that Quiz has not be initialized yet
			throws new exception("Quiz needs to be initiated first");
		}
		for (int X = 0;X < MAXPROBLEMS; X++)
		{
			int first = rnd.nextInt(MAXNUMBERUSED);
			int second = rnd.nextInt(MAXNUMBERUSED);
			int total = first + second;
			
			Problems[X][0] = first;
			Problems[X][1] = second;
			Answers[X] = total;
			Guesses[X] = 0;
		}
		quizUsedCount++;
	}
}
