package yfdyf.myapplication.View;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.DecelerateInterpolator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * author：Bolex on 2016/9/8 14:22
 */
public class DiffuseView extends SurfaceView implements SurfaceHolder.Callback {

    private Paint p;
    float lastx = -100;
    float lastY = -100;
    //扩散动画
    private ValueAnimator diffuseController;
    private float diffuseValue = 1;
    private Integer mycoler = Integer.valueOf("-1" + randomText(6));
    Integer integer = Integer.valueOf(randomText(2));


    public DiffuseView(Context context) {
        super(context);
        init();
    }

    public DiffuseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DiffuseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        p = new Paint();
        getHolder().addCallback(this);
        initAnimator();
    }

    private void initAnimator() {
        diffuseController = ValueAnimator.ofFloat(0, 1);
        diffuseController.setDuration(4000);
        diffuseController.setInterpolator(new DecelerateInterpolator());
        diffuseController.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {


            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                diffuseValue = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int Y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                break;
                case  MotionEvent.ACTION_UP:
                float offsetX = x - lastx;
                float offsetY = Y - lastY;
                System.out.println("X:" + offsetX + "y:" + offsetY);
                lastx = x;
                lastY = Y;
                integer = Integer.valueOf(randomText(4));
                mycoler = Integer.valueOf("-1" + randomText(6));
                diffuseController.start();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        p.setColor(mycoler);
        p.setAntiAlias(true);
        canvas.drawCircle(lastx, lastY, integer * diffuseValue, p);
        super.onDraw(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //锁定画布
        Canvas canvas = holder.lockCanvas();
        draw(canvas);
        //接触锁定
        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private String randomText(int lenth) {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < lenth) {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set) {
            sb.append("" + i);
        }

        return sb.toString();
    }
}
