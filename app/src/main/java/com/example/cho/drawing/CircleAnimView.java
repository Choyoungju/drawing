package com.example.cho.drawing;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by cho on 2015-04-01.
 */
public class CircleAnimView extends View{

    private Paint mCirclePaint;
    private float radious;
    private ValueAnimator moveAnim;
    private LinearInterpolator linearInterpolator;
    private AccelerateInterpolator accelerateInterpolator;

    public CircleAnimView(Context context)
    {
        super(context);
        setBackgroundColor(Color.BLACK);

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);

        accelerateInterpolator = new AccelerateInterpolator();
        linearInterpolator = new LinearInterpolator();

        moveAnim = ValueAnimator.ofFloat(10.0f, 300.0f);
        moveAnim.setDuration(3000);
        moveAnim.setInterpolator(accelerateInterpolator);
        moveAnim.setRepeatCount(ValueAnimator.INFINITE);
        moveAnim.setRepeatMode(ValueAnimator.REVERSE);
        moveAnim.addUpdateListener(new ObjectAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation){
                radious = (Float) (animation.getAnimatedValue());
                invalidate();
            }
        });
        moveAnim.start();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        mCirclePaint.setShader(new RadialGradient(getMeasuredWidth()/2,
                                                    getMeasuredHeight()/2,
                                                    radious,
                                                    Color.YELLOW,
                                                    Color.RED,
                Shader.TileMode.CLAMP));

        canvas.drawCircle(getMeasuredWidth()/2, getMeasuredHeight()/2, radious,mCirclePaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                moveAnim.setDuration(500);
                moveAnim.setInterpolator(linearInterpolator);
                break;
            case MotionEvent.ACTION_UP:
                moveAnim.setDuration(3000);
                moveAnim.setInterpolator(accelerateInterpolator);
                break;
        }
        return true;
    }
}
