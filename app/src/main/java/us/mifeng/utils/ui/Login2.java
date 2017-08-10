package us.mifeng.utils.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import us.mifeng.utils.R;
import us.mifeng.utils.adapter.VPAdapter;
import us.mifeng.utils.utils.SharedUtils;

/**
 * Created by shido on 2017/6/27.
 */

public class Login2 extends Activity {
    private int ss [] = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    private ViewPager mVp;
    private List<ImageView> list;
    private Button mBtn;
    private SharedUtils sharedUtils;
    private String tag = "first";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login2);
        mVp = (ViewPager) findViewById(R.id.mVp);
        mBtn = (Button) findViewById(R.id.login2_mBtn);
        initList();
        initView();
        sharedUtils = new SharedUtils();
        sharedUtils.saveShared(Login2.this,"tag",tag);

    }

    private void initView() {
        mVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position==3){
                    mBtn.setVisibility(View.VISIBLE);
                }else {
                    mBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        VPAdapter adapter = new VPAdapter(list,Login2.this);
        mVp.setAdapter(adapter);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login2.this,MainActivity.class));
                finish();
            }
        });
    }

    private void initList() {
        list = new ArrayList<>();
        for (int i = 0 ;i<ss.length;i++){
            ImageView imageView = new ImageView(Login2.this);
            imageView.setImageResource(ss[i]);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            list.add(imageView);
        }
    }
}
