package us.mifeng.utils.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.viewpagerindicator.TabPageIndicator;

import us.mifeng.utils.R;
import us.mifeng.utils.base.BaseFragment;
import us.mifeng.utils.hmfrag.TuiJianFragment;
import us.mifeng.utils.ui.MipcaActivityCapture;
import us.mifeng.utils.utils.ToSi;

/**
 * Created by shido on 2017/6/28.
 */
/**
 *首页fragment
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private final static int SCANNIN_GREQUEST_CODE = 1;
    private static final String[] TITLE = new String[] { "推荐", "限时购", "新品", "居家",
            "餐厨", "配件", "服装", "洗护","婴童","杂货","饮食",
            "志趣"};

    @Override
    protected View initView() {
        View inflate = View.inflate(getActivity(), R.layout.homefragment, null);
        //ViewPager的adapter
        FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getFragmentManager());
        ViewPager pager = (ViewPager)inflate.findViewById(R.id.pager);
        LinearLayout saoyisao = (LinearLayout) inflate.findViewById(R.id.saoyisao);

        pager.setAdapter(adapter);

        saoyisao.setOnClickListener(this);

        //实例化TabPageIndicator然后设置ViewPager与之关联
        TabPageIndicator indicator = (TabPageIndicator)inflate.findViewById(R.id.indicator);
        indicator.setViewPager(pager);

        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                ToSi.show(getActivity(),TITLE[position]);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return inflate;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saoyisao:
                Intent intent = new Intent();
                intent.setClass(getActivity(), MipcaActivityCapture.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
                break;
        }
    }



    /**
     * 适配器代码
     */
    class TabPageIndicatorAdapter extends FragmentPagerAdapter {
        public TabPageIndicatorAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //这里是为了清楚TabPageIndicator时报的ViewPager has not been bound异常，先给TabPageIndicator设置android:visibility="gone"
            if (position==0){
                Fragment tuijian = new TuiJianFragment();
                return tuijian;
            }else {
                Fragment fragment = new HomeFragment_shouye();
                Bundle args = new Bundle();
                args.putString("arg", TITLE[position]);
                fragment.setArguments(args);

                return fragment;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE[position % TITLE.length];
        }

        @Override
        public int getCount() {
            return TITLE.length;
        }
    }


    @Override
    protected void initList() {

    }
}
