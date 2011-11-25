package com.barakisbrown;

import java.util.Random;

public class Quiz 
{
	private Random rnd = null;
	private static Quiz rtnObject = null;
	private static final int MAXPROBLEMS = 5;
	private static final int MAXNUMBERUSED = 21;
	
	private int []Answers = {0,0,0,0,0};
	private int []Guesses = {0,0,0,0,0};
	private int [][]Problems = {{0,0,0,0,0},{0,0,0,0,0}};
	
	private int quizUsedCount = 0;
	

	private Quiz()
	{
		if (rnd == null)
			rnd = new Random();
	}
	
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
	
	private boolean isCorrect(int index)
	{
		if (Answers[index] == Guesses[index])
			return true;
		else
			return false;
	}
	
	public void setGuess(int Guess,int index)
	{
		Guesses[index] = Guess;
	}
	
	public int determineScore()
	{
		int numRight = 0;
		
		for (int index = 0;index < MAXPROBLEMS; index++)
		{
			if (isCorrect(index))
				numRight++;
		}
		
		return numRight / MAXPROBLEMS;
	}
	
	public void LoadQuiz()
	{
		// Since Quiz() is first initialized, then simply fill in Problems and Answers Array
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
