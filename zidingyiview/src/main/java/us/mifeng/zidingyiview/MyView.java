package us.mifeng.zidingyiview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by shido on 2017/9/11.
 */

public class MyView extends View {
    private int rwidth,rwicthGreen;
    private float radius,radius_green;
    private Paint paint,paintGreen;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    //初始化
    void init(){
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.red));
        //设置粗度
        paint.setStrokeWidth(20);
        //设置抗锯齿，让画笔更圆滑
        paint.setStyle(Paint.Style.STROKE);
        //设置画笔模式，只有边，没有填充色
        paint.setAntiAlias(true);

        paintGreen = new Paint();
        paintGreen.setStrokeWidth(10);
        paintGreen.setColor(getResources().getColor(R.color.green));
        paintGreen.setStyle(Paint.Style.STROKE);
        paintGreen.setAntiAlias(true);
        if (rwidth==0){
            radius_green = getResources().getDimensionPixelSize(R.dimen.radius);
        }else {
            radius_green = ((rwidth-2* paint.getStrokeWidth())/2);
        }
    }
    //在onMeasure中不能初始化画笔等其他空间
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       //宽高自己定
        if (MeasureSpec.getMode(widthMeasureSpec)==MeasureSpec.AT_MOST||MeasureSpec.getMode(heightMeasureSpec)==MeasureSpec.AT_MOST){
            int width = (int) (2*radius+2*paint.getStrokeWidth());
            int height = (int) (2*radius+2*paint.getStrokeWidth());
            setMeasuredDimension(width,height);
        }else {
            int size = MeasureSpec.getSize(widthMeasureSpec);
            rwidth=size;
            radius = (rwidth-2*paint.getStrokeWidth())/2;
            //让drown在调用一次
            postInvalidate();
            int hSize = MeasureSpec.getSize(heightMeasureSpec);
            int wm = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
            int hm = MeasureSpec.makeMeasureSpec(hSize, MeasureSpec.EXACTLY);
            super.onMeasure(wm,hm);

        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        float strokeWidth = paint.getStrokeWidth();
        float strokeWidthGreen = paintGreen.getStrokeWidth();
       // canvas.drawCircle(radius_green+strokeWidthGreen,radius_green+strokeWidthGreen,radius_green,paintGreen);

        canvas.drawCircle(radius+strokeWidth,radius+strokeWidth,radius,paint);
        //canvas.drawArc(strokeWidth,strokeWidth,strokeWidthGreen,strokeWidthGreen,-90.0f,0.0f,false,paint);
    }
}
