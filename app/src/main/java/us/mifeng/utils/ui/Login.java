package us.mifeng.utils.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import us.mifeng.utils.R;
import us.mifeng.utils.utils.SharedUtils;

/**
 * Created by shido on 2017/6/27.
 */

public class Login extends Activity {
    boolean xx=true;
    private TextView time;
    private int inex = 5;
    private LinearLayout ll_time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ll_time = (LinearLayout) findViewById(R.id.ll_time);
        time = (TextView) findViewById(R.id.time);
        ll_time.setVisibility(View.VISIBLE);
        time.setText(inex+"");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (xx){
                            try {
                                Thread.sleep(1000);
                                hand.sendEmptyMessage(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }
    Handler hand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==100){
                inex--;
                time.setText(inex+"");
                if (inex==0){
                    xx=false;
                    ll_time.setVisibility(View.GONE);
                    String tag = new SharedUtils().getShared(Login.this, "tag");
                    if (tag.equals("")){
                        startActivity(new Intent(Login.this,Login2.class));
                    }else {
                        startActivity(new Intent(Login.this,MainActivity.class));
                    }
                    finish();
                }
            }
        }
    };
}
