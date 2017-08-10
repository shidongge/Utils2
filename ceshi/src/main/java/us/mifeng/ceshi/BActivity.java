package us.mifeng.ceshi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import us.mifeng.ceshi.utils.FirstEvent;


/**
 * Created by shido on 2017/8/9.
 */

public class BActivity extends Activity {

    private TextView mtv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bactivity);

        EventBus.getDefault().register(this);


        Button mBtn = (Button) findViewById(R.id.b_mBtn);
        mtv = (TextView) findViewById(R.id.b_mTv);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BActivity.this, CActivity.class));

            }
        });
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEvent(FirstEvent event) {
        String msg = "我是收到的消息：" + event.getMsg();
        Log.d("harvic", msg);
        mtv.setText(msg);

    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
}