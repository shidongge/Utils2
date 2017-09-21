package us.mifeng.utils.app;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by shido on 2017/9/8.
 */

public class APP extends Application {
    private Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        initTongMing();
        Fresco.initialize(this);

    }

    private void initTongMing() {

    }

}
