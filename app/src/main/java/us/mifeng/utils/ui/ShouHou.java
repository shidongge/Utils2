package us.mifeng.utils.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import us.mifeng.utils.R;

/**
 * Created by shido on 2017/8/10.
 */

public class ShouHou extends Activity implements View.OnClickListener {

    private LinearLayout tuihuo;
    private LinearLayout huanhuo;
    private LinearLayout jilu;
    private LinearLayout kefu;
    private ImageView back;
    private String tuihuan;
    private TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shouhou);
        tuihuan = getIntent().getStringExtra("tuihuan");
        initView();
        SetText();
    }

    private void SetText() {
        title.setText(tuihuan);
    }

    private void initView() {
        tuihuo = (LinearLayout) findViewById(R.id.shouhou_tuihuo);
        huanhuo = (LinearLayout) findViewById(R.id.shouhou_huanhuo);
        jilu = (LinearLayout) findViewById(R.id.shouhou_jilu);
        kefu = (LinearLayout) findViewById(R.id.shouhou_kefu);
        back = (ImageView) findViewById(R.id.shouhou_back);
        title = (TextView) findViewById(R.id.shouhou_title);
        tuihuo.setOnClickListener(this);
        huanhuo.setOnClickListener(this);
        jilu.setOnClickListener(this);
        kefu.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shouhou_tuihuo:
                Intent intent1 = new Intent(ShouHou.this, TuiHuo.class);
                intent1.putExtra("tuihuo","没有符合退货条件的商品");
                intent1.putExtra("shouhou","申请退货");
                startActivity(intent1);
                break;
            case R.id.shouhou_huanhuo:
                Intent intent2 = new Intent(ShouHou.this, TuiHuo.class);
                intent2.putExtra("tuihuo","没有符合换货条件的商品");
                intent2.putExtra("shouhou","申请换货");
                startActivity(intent2);
                break;
            case R.id.shouhou_jilu:
                Intent intent3 = new Intent(ShouHou.this, TuiHuo.class);
                intent3.putExtra("tuihuo","你对购买的商品都很满意");
                intent3.putExtra("shouhou","售后记录");
                startActivity(intent3);
                break;
            case R.id.shouhou_kefu:

                break;
            case R.id.shouhou_back:
                finish();
                break;
        }
    }
}
