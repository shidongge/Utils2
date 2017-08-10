package us.mifeng.ceshi;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import us.mifeng.ceshi.utils.FirstEvent;

/**
 * Created by shido on 2017/8/9.
 */

public class CActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cactivity);
        Button c_mBtn = (Button) findViewById(R.id.c_mBtn);
        c_mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new FirstEvent("内容是"));
                finish();
            }
        });
    }
}
