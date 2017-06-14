package com.example.cho.drawing;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by cho on 2015-04-01.
 */
public class RectAnimView extends View{

    private Paint mRectPaint;
    private float moveX;
    private ValueAnimator moveAnim;
    private LinearInterpolator linearInterpolator;
    private AccelerateInterpolator accelerateInterpolator;

    public RectAnimView (Context context){
        super(context);
        setBackgroundColor(Color.BLACK);

        mRectPaint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.android);
        Bitmap scaleBitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, false);
        mRectPaint.setShader(new BitmapShader(scaleBitmap, Shader.TileMode.MIRROR,  Shader.TileMode.MIRROR));

        linearInterpolator = new LinearInterpolator();
        accelerateInterpolator = new AccelerateInterpolator();

        moveAnim = ValueAnimator.ofFloat(0.0f, 500,0f);
        moveAnim.setDuration(3000);
        moveAnim.setRepeatMode(ValueAnimator.REVERSE);
        moveAnim.setRepeatCount(ValueAnimator.INFINITE);
        moveAnim.addUpdateListener(new ObjectAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation){
                moveX = (Float) animation.getAnimatedValue();
                invalidate();
            }
        });
     moveAnim.start();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(moveX, getMeasuredHeight()/2 - 150.0f, moveX + 300.0f, getMeasuredHeight()/2 + 150.0f,mRectPaint);

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
                moveAnim.setInterpolator(linearInterpolator);
                break;
        }
        return true;
    }

}
