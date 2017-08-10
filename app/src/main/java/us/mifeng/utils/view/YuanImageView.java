package us.mifeng.utils.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by shido on 2017/8/8.
 */

@SuppressLint("AppCompatCustomView")
public class YuanImageView extends ImageView{

    public YuanImageView(Context context) {
        super(context);
    }

    public YuanImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public YuanImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 强制设置圆形图片控件的宽高一致
        int min = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(min, min);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();

        if (drawable == null) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        // 注意：此条语句不能智能提示，只能手动输入
        Bitmap b = ((BitmapDrawable) drawable).getBitmap();

        if (null == b) {
            return;
        }

        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

        // 因为后来在onMeasure中强制设置了控件宽高相等，所以相当于下面的w强制等于h
        // min == w == h == sbmp.getWidth() == sbmp.getHeight()是成立的
        int w = getWidth(), h = getHeight();
        int min = Math.min(w, h);
        Bitmap roundBitmap = getCroppedBitmap(bitmap, min);
        canvas.drawBitmap(roundBitmap, 0, 0, null);

    }
    /**
     * 截取bmp中间区域的图像，并缩放至与视图宽高大小一致。画图。
     *
     * @param bmp
     *            Bitmap对象
     * @param min
     *            视图宽高的最小值，单位：px
     * @return bitmap
     */
    public static Bitmap getCroppedBitmap(Bitmap bmp, int min) {
        // 截取后的bitmap
        Bitmap squareBitmap;
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();
        int squareWidth = 0, squareHeight = 0;
        int x = 0, y = 0;
        if (bmpHeight > bmpWidth) {// 高大于宽
            squareWidth = squareHeight = bmpWidth;
            x = 0;
            y = (bmpHeight - bmpWidth) / 2;
            // 截取正方形图片
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,
                    squareHeight);
        } else if (bmpHeight < bmpWidth) {// 宽大于高
            squareWidth = squareHeight = bmpHeight;
            x = (bmpWidth - bmpHeight) / 2;
            y = 0;
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,
                    squareHeight);
        } else {
            squareBitmap = bmp;
        }

        Bitmap sbmp;
        //对截取后的squareBitmap缩放至控件宽高
        if (squareBitmap.getWidth() != min || squareBitmap.getHeight() != min) {
            sbmp = Bitmap.createScaledBitmap(squareBitmap, min, min, false);
        } else {
            sbmp = squareBitmap;
        }
        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(),
                Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        //指定抠图/画图区域
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);//将内容以外的区域设置为完全透明的黑色
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(sbmp.getWidth() / 2 + 0.7f,
                sbmp.getHeight() / 2 + 0.7f, min / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);

        return output;
    }

}
