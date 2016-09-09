package yfdyf.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GuaguaKaView extends View {
    private Paint mPaint, mTPaint;
    private Rect mbound = new Rect();
    private Bitmap mBitmap;
    private Path mPath;
    private Bitmap mHongbao;
    private Canvas mCanvas;

    public GuaguaKaView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub  
    }

    public GuaguaKaView(Context context) {
        super(context, null);
        // TODO Auto-generated constructor stub  
    }

    private Context mContext;

    public GuaguaKaView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mContext = context;
        //初始化画笔  
        initPaint();
        //初始化path  
        initPath();
        //初始化图片资源  
        setUpBitmap();

    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(15);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        // mPaint.setColor(0xaa0000ff);  

        mTPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTPaint.setStyle(Paint.Style.FILL);
        mTPaint.setStrokeCap(Paint.Cap.ROUND);
        mTPaint.setDither(true);
        mTPaint.setColor(0xaaff0000);
        mTPaint.setTextSize(30);
        measureText();

    }

    private void setUpBitmap() {
        //先绘制dst  再设置xfermode  最后绘制src  
        mBitmap = BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.jj);
        mHongbao = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(),
                Config.ARGB_8888);
        mCanvas = new Canvas(mHongbao);
        mCanvas.drawColor(Color.parseColor("#c0c0c0"));
        mCanvas.drawBitmap(mBitmap, 0, 0, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(mHongbao.getWidth(),
                    MeasureSpec.AT_MOST);
        }
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                    mHongbao.getHeight(), MeasureSpec.AT_MOST);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initPath() {
        mPath = new Path();

    }

    private String text = "1000.000万元";

    void measureText() {
        mTPaint.getTextBounds(text, 0, text.length(), mbound);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText(text, getWidth() / 2 - mbound.width() / 2, getHeight()
                / 2 + mbound.height() / 2, mTPaint);
        if (mComplete)
            return;
        drawPath();

        canvas.drawBitmap(mHongbao, 0, 0, null);

    }

    /**
     * 设置Xfermode 模式  PorterDuff.Mode.DST_OUT 取下层绘制非交集部分
     */
    private void drawPath() {

        mPaint.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
        mCanvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        int downx = 0;
        int downy = 0;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downx = x;
                downy = y;

                mPath.moveTo(downx, downy);
                break;

            case MotionEvent.ACTION_MOVE:
                int distanceX = x - downx;
                int distanceY = x - downy;
                //当触摸超过距离5的时候才刮
                if (Math.abs(distanceX) > 5 || Math.abs(distanceY) > 5) {

                    mPath.lineTo(x, y);
                    invalidate();
                }
                downx = x;
                downy = y;
                break;
            case MotionEvent.ACTION_UP:
                cheakArea();
                break;
        }

        return true;
    }

    private boolean mComplete = false;

    private void cheakArea() {
        new Thread(new Runnable() {
            int w = mHongbao.getWidth();
            int h = mHongbao.getHeight();
            //红包的像素总合  
            int HBATotolrea = w * h;
            //存储图片像素点的值  
            int[] mHongbaoArea = new int[HBATotolrea];
            //当前面积  
            int mCurArea;
            private String TAG;

            @Override
            public void run() {
                //得到每个像素的值  
                mHongbao.getPixels(mHongbaoArea, 0, w, 0, 0, w, h);
                for (int i = 0; i < w; i++) {
                    for (int j = 0; j < h; j++) {
                        int index = w * i + j;
                        if (mHongbaoArea[index] == 0) {
                            mCurArea += 1;

                        }

                    }

                    int a = mCurArea * 100 / HBATotolrea;

                    //当刮到大于60%的时候 就显示结果  
                    if (a > 60) {
                        mComplete = true;
                    }

                }

            }

        }).start();

    }
}