package com.example.cho.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by cho on 2015-04-01.
 */


public class ArcAnimView extends View {

    private Paint mPaint;
    private RectF mBigOval;
    private float mStart, mSweep;
    private static final float SWEEP_INC = 2;
    private static final float START_INC = 15;
    private boolean bAction = false;

    public ArcAnimView(Context context){
        super(context);
        setBackgroundColor(Color.BLACK);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setShader(new SweepGradient(300,600,Color.YELLOW, Color.RED));
        mBigOval = new RectF(300 - 150,
                                600 - 150,
                            300+ 150,
                            600+150);

    }

    @Override
    protected  void onDraw(Canvas canvas)
    {
        if (bAction)
        {
            canvas.drawArc(mBigOval,mStart,mSweep,true,mPaint);
            mSweep += SWEEP_INC;
            if(mSweep>360){
                mSweep -=360;
            }
        }
       invalidate();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                bAction = true;
                break;
            case MotionEvent.ACTION_UP:
                bAction = false;
                break;

        }
        return true;
    }

}
