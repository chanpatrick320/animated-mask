package hk.patsolution.animationentrydemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by patrickchan on 22/1/15.
 */
public class CircleMaskView extends View implements ValueAnimator.AnimatorUpdateListener {
    private int radius;
    private Paint paint;
    private Paint normal,clear;
    private Bitmap bitmap ,foreground;

    public CircleMaskView(Context c){
        super(c);

    }

    public CircleMaskView(Context c, AttributeSet attr){
        super(c, attr);
        paint=new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        paint.setColor(Color.WHITE);
        clear=new Paint();
        clear.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        normal=new Paint();
        normal.setFilterBitmap(true);

    }

    public void startAnimation(int duration){
        ValueAnimator v=ValueAnimator.ofInt(0,100);
        v.setStartDelay(0);
        v.setDuration(duration);
        v.addUpdateListener(this);
        v.start();

    }

    public void setBitmap(Bitmap b){
        bitmap= b;
        foreground=Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

    }



    @Override
    protected void onDraw(Canvas c){
        super.onDraw(c);
        c.drawBitmap(foreground,0,0,normal);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        radius=(int)animation.getAnimatedValue();
        Canvas c=new Canvas();
        c.setBitmap(foreground);
        c.drawRect(0, 0, foreground.getWidth(), foreground.getHeight(), clear);
        c.drawCircle(this.getMeasuredWidth()/2,this.getMeasuredHeight()/2,radius*this.getMeasuredWidth()/100,normal);

        c.drawBitmap(bitmap, 0, 0, paint);


        invalidate();
    }
}
