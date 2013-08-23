package com.barakisbrown;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class ProblemRow 
{
    private int problemNumber;
    private ProblemBase problem;
    private ProblemBuilder builder;
    
    private LinearLayout probNumber;
    private LinearLayout probItself;
    private LinearLayout probComplete;
    private LinearLayout probAnswer;
    private LinearLayout probGuess;
    private Context mContext;
    
    public ProblemRow(Context ctx,int number,ProblemBase prob)
    {
        problemNumber = number;
        problem = prob;
        mContext = ctx;
        builder = new ProblemBuilder(mContext,problem);
        
        probNumber = (LinearLayout)builder.buildItem(problemNumber);
        probItself = (LinearLayout)builder.buildProblem();
        probComplete = (LinearLayout)builder.buildCompleteProblem();
        probAnswer = (LinearLayout)builder.buildItem(prob.getTotal());
        probGuess  = (LinearLayout)builder.buildItem(prob.getGuess());
        
    }

    public View getProblemNumber()
    {
        return probNumber;
    }
    
    public View getProblem()
    {
        return probItself;
    }
    
    public View getCompeleteProblem()
    {
        return probComplete;
    }
    
    public View getGuess()
    {
        return probGuess;
    }
    
    public View getAnswer()
    {
        return probAnswer;
    }
    
    
}
