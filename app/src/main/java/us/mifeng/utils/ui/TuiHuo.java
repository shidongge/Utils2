package us.mifeng.utils.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import us.mifeng.utils.R;
import us.mifeng.utils.base.BaseActivity;

/**
 * Created by shido on 2017/8/10.
 */
/***
 *
 *这是退货，换货，售后记录的activity
 * 判断是否有退货，换货或售后，相当于判断list是否为空，
 * 如果为空，就用默认的布局
 * 如果不为空就让listview显示出来
 * 这里没有list的bean 所以用的就是默认布局
 */

public class TuiHuo extends BaseActivity implements View.OnClickListener {
    private List<String> list;
    private TextView mTv;
    private ListView mLv;
    private TextView title;
    private String tuihuo;
    private String shouhou;
    private ImageView back;

    @Override
    protected void initView() {
        setContentView(R.layout.tuihuo);
        mTv = (TextView) findViewById(R.id.tuihuo_mTv);
        mLv = (ListView) findViewById(R.id.tuihuo_mLv);
        title = (TextView) findViewById(R.id.shouhou_title);
        back = (ImageView) findViewById(R.id.shouhou_back);
        tuihuo = getIntent().getStringExtra("tuihuo");
        shouhou = getIntent().getStringExtra("shouhou");

        back.setOnClickListener(this);
    }

    @Override
    protected void initList() {
        mTv.setText(tuihuo);
        title.setText(shouhou);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shouhou_back:
                finish();
                break;
        }
    }
}
