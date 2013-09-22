package com.barakisbrown;

import java.util.Iterator;

import android.content.Context;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;

public class ProblemBuilder extends ProblemHelper 
{
    private ImageView plusSign;
    private ImageView equalSign;
    private Space space;
    private Context ctx;
    
    
    public ProblemBuilder(Context context) 
    {
        super();
        ctx = context;
        
        // create my views
        plusSign = new ImageView(ctx);
        plusSign.setScaleType(ImageView.ScaleType.FIT_CENTER);
        plusSign.setImageResource(R.drawable.add);
        // equal sign
        equalSign = new ImageView(ctx);
        equalSign.setScaleType(ImageView.ScaleType.FIT_CENTER);
        equalSign.setImageResource(R.drawable.equalsign);
        // space widget
        space = new Space(ctx);
    }
    
    
    public ProblemBuilder(Context context,ProblemBase base)
    {
        ctx = context;
        setLeftSide(base.getLeftSide());
        setRightSide(base.getRightSide());
    }
    
    /**
     * Takes a problem in this syntax [problem + problem =] and then displays it to the screen by
     * actually building the problem graphically. 
     * @return View which will contain the problem to be displayed to the screen
    */
    public View buildProblem()
    {
        
        LinearLayout rtnView = new LinearLayout(ctx);
        rtnView.setOrientation(LinearLayout.HORIZONTAL);
        rtnView.setLayoutParams(new GridLayout.LayoutParams());
        
        // Build LeftSide
        Iterator<Integer> itor = buildLeftSide();
        ImageView iv;
        // loop through of iterator
        while(itor.hasNext())
        {
            iv = new ImageView(ctx);
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            iv.setImageResource(itor.next());
            itor.remove();
            rtnView.addView(iv);
        }
        // Space
        rtnView.addView(space);
        // Operator
        rtnView.addView(plusSign);
        // Build LeftSide
        itor = buildRightSide();
        // another loop
        while(itor.hasNext())
        {
            iv = new ImageView(ctx);
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            iv.setImageResource(itor.next());
            itor.remove();
            rtnView.addView(iv);
        }
        // Space
        rtnView.addView(space);
        // Equal Sign
        rtnView.addView(equalSign);
        // Space
        rtnView.addView(space);
        // return the view
        return rtnView;
    }
   
   /**
    * Builds a complete problem including the answer by the user
    * Used to show the customer what they missed.
    * @return View
   */
   public View buildCompleteProblem()
   {
       int total = this.LeftSide + this.RightSide;
       LinearLayout rtnView = (LinearLayout)buildProblem();
       rtnView.addView(buildNumberImage(total));
       return rtnView;
       
   }
    
   
   /**
    * 
    * @param Item integer that needs to be converted into an image
    * @return View
    */
   public LinearLayout buildNumberImage(int item)
   {
        LinearLayout rtnView = new LinearLayout(ctx);
        rtnView.setOrientation(LinearLayout.HORIZONTAL);
        rtnView.setLayoutParams(new GridLayout.LayoutParams());
        
        // Build it below
        ImageView iv;
        Iterator<Integer> itor = builder(item);
        // another loop
        while(itor.hasNext())
        {
            iv = new ImageView(ctx);
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            iv.setImageResource(itor.next());
            itor.remove();
            rtnView.addView(iv);
        }
        
        return rtnView;
   }
   
   
   public LinearLayout buildProblem(LinearLayout dynLayout)
   {
       // Build Images for the LeftSide and RightSide of the operator
      
       // assign them
       LinearLayout Left  = buildNumberImage(LeftSide);
       LinearLayout Right = buildNumberImage(RightSide); 
       // Add Space at Start
       dynLayout.addView(space);
       // Add LeftSide
       dynLayout.addView(Left);
       // Space
       dynLayout.addView(space);
       // Operator
       dynLayout.addView(plusSign);
       // Space
       dynLayout.addView(space);
       // Build RightSide
       dynLayout.addView(Right);
       // Space
       dynLayout.addView(space);
       // Equal Sign
       dynLayout.addView(equalSign);
       // Space
       dynLayout.addView(space);
       // return the view
       return dynLayout;
   }

}
