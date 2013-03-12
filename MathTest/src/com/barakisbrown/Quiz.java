package com.barakisbrown;

import java.util.Random;

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
 * 3/12/13 Modification
 * I am changing this class so that it can make different amount of problems ranging from
 * 1 to 10. If done right, theoretically it can handle anything the user sends to it.
 * 
 * @author barakis mailto:barakis@barakisbrown.com
 * @version 1.0
 *
 */
public class Quiz
{
	private Random rnd = null;
	private static Quiz rtnObject = null;
	private static int MAXPROBLEMS = 0;
	private static final int MAXNUMBERUSED = 99;
	private int numCorrect = 0;
	
	private int []Answers;
	private int []Guesses;
	private int[][] Problems;
	
	
	private int quizUsedCount = 1;
	private double score = 0;

	private Quiz(int problems)
	{
		if (rnd == null)
		{
			MAXPROBLEMS = problems;
			rnd = new Random();
			Problems = new int[MAXPROBLEMS][2];
			Answers = new int[MAXPROBLEMS];
			Guesses = new int[MAXPROBLEMS];
		}
	}
	

	/**
	 * If Quiz static object has not been create it then call private constructor which will then
	 * create the random object generator.  If Quiz object has been created then simply return it
	 * back to the calling function.
	 * @return com.barakisbrown.Quiz
	 * @param problems -- the number of problems for the quiz to generate.
	 */
	static public Quiz initQuiz(int problems)
	{
		if (rtnObject == null)
		{
			if (problems < 1)
				return null;
			else
			{
				rtnObject = new Quiz(problems);
				return rtnObject;
			}
			
		}
		return rtnObject;
	}
	
	public int getQuizNumber()     { return quizUsedCount; }
	public int getFirst(int index) { return Problems[index][0]; }
	public int getSecond(int index) { return Problems[index][1]; }
	public int getAnswer(int index) {  return Answers[index]; }
	public int getNumProblems()     { return MAXPROBLEMS; }
	public int getNumCorrect()      { return numCorrect; }
	public double getScore()        { return score; }
	
	/**
	 * Checks the Answers Array versus the Guess Array at the index to determine if the
	 * Guess is correct or not
	 * @param index
	 * @return true Guess = Answer, false otherwise
	 * @throws Exception 
	 */
	private boolean isCorrect(int index) throws Exception
	{
		if (index < 0 || index > MAXPROBLEMS)
		{
			throw new Exception("Wrong Index Value");
		}
		else
		{
			if (Answers[index] == Guesses[index])
				return true;
			else
				return false;
		}
		
	}
	
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
			throw new Exception("Wrong Index Value");
		}
		else
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
			try
			{
				if (isCorrect(index))
					numRight++;
			}catch(Exception Ex)
			{
				System.out.println(Ex.getMessage());
			}
			
		}
		
		numCorrect = numRight;
		score = (double)numCorrect / MAXPROBLEMS;
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
			throw new Exception("Quiz needs to be initiated first");
		}
		// since rnd exists then I will need to create the loop that makes the quiz
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
