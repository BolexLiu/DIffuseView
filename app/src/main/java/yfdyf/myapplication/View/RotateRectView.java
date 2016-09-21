package yfdyf.myapplication.View;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * author：LiuShenEn on 2016/9/20 16:29
 */
public class RotateRectView extends View {
    Paint mPaint = new Paint();
    private int mWidth;
    private int mheight;
    int mrRectFWidth = 600;
    //缩放动画
    private ValueAnimator toBigController;
    private ValueAnimator tominController;

//旋转
    private ValueAnimator RotateController;
    private float diffuseValue = 1;
    private float RotateValue=1;

    public RotateRectView(Context context) {
        super(context);
        init(context);
    }

    public RotateRectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RotateRectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RotateRectView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    void init(Context mContext) {
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mheight = h;
        initAnimator();
        toBigController.start();
    }

    private void initAnimator() {
        toBigController = ValueAnimator.ofFloat(0, 1);
        toBigController.setDuration(5000);
        toBigController.setInterpolator(new DecelerateInterpolator());
        toBigController.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                diffuseValue = (float) animation.getAnimatedValue();
                postInvalidate();
            }

        });

        tominController = ValueAnimator.ofFloat(1, 0);
        tominController.setDuration(5000);
        tominController.setInterpolator(new DecelerateInterpolator());
        tominController.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                diffuseValue = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });

        //旋转
        RotateController = ValueAnimator.ofFloat(1, 0);
        RotateController.setDuration(5000);
        RotateController.setRepeatCount(ValueAnimator.INFINITE);
        RotateController.setInterpolator(new DecelerateInterpolator());
        RotateController.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                RotateValue = (float) animation.getAnimatedValue();

            }
        });


        animator(toBigController, tominController);
        animator(tominController, toBigController);
        RotateController.start();
    }
    public void animator(ValueAnimator endValueAnimator, final ValueAnimator starValueAnimator) {
        endValueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                starValueAnimator.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mWidth / 2, mheight / 2);
        RectF mrRectF = new RectF(0, 0, mrRectFWidth * diffuseValue, mrRectFWidth * diffuseValue);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(0, -1300, 0, 1300, mPaint);
        canvas.drawLine(-1500, 0, 1500, 0, mPaint);
//        canvas.drawRect(mrRectF, mPaint);
        for (int i = 0; i <= 36; i++) {
            canvas.scale(0.9f, 0.9f);
            canvas.rotate(RotateValue*360);
            canvas.drawRect(mrRectF, mPaint);
        }
    }
}
