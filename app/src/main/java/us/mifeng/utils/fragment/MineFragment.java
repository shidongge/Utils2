package us.mifeng.utils.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import us.mifeng.utils.R;
import us.mifeng.utils.base.BaseFragment;
import us.mifeng.utils.ui.DingDan;
import us.mifeng.utils.ui.ShouHou;

/**
 * Created by shido on 2017/6/28.
 */

/**
 * 个人fragment
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout myDingDan;
    private LinearLayout fukuan;
    private LinearLayout daifahuo;
    private LinearLayout yifahuo;
    private LinearLayout daipingjia;
    private LinearLayout shouhou;

    @Override
    protected View initView() {
        View inflate = View.inflate(getActivity(), R.layout.minefragment, null);
        myDingDan = (LinearLayout) inflate.findViewById(R.id.MyDingDan);
        fukuan = (LinearLayout) inflate.findViewById(R.id.dingdan_daifukuan);
        daifahuo = (LinearLayout) inflate.findViewById(R.id.dingdan_daifahuo);
        yifahuo = (LinearLayout) inflate.findViewById(R.id.dingdan_yifahuo);
        daipingjia = (LinearLayout) inflate.findViewById(R.id.dingdan_daipingjia);
        shouhou = (LinearLayout) inflate.findViewById(R.id.dingdan_shouhou);
        return inflate;
    }

    @Override
    protected void initList() {
        myDingDan.setOnClickListener(this);
        fukuan.setOnClickListener(this);
        daifahuo.setOnClickListener(this);
        yifahuo.setOnClickListener(this);
        daipingjia.setOnClickListener(this);
        shouhou.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.MyDingDan:
                Intent intent = new Intent(getActivity(),DingDan.class);
                intent.putExtra("intent","0");
                startActivity(intent);
                break;
            case R.id.dingdan_daifukuan:
                Intent intent1 = new Intent(getActivity(),DingDan.class);
                intent1.putExtra("intent","1");
                startActivity(intent1);
                break;
            case R.id.dingdan_daifahuo:
                Intent intent2 = new Intent(getActivity(),DingDan.class);
                intent2.putExtra("intent","2");
                startActivity(intent2);
                break;
            case R.id.dingdan_yifahuo:
                Intent intent3 = new Intent(getActivity(),DingDan.class);
                intent3.putExtra("intent","3");
                startActivity(intent3);
                break;
            case R.id.dingdan_daipingjia:
                Intent intent4 = new Intent(getActivity(),DingDan.class);
                intent4.putExtra("intent","4");
                startActivity(intent4);
                break;
            case R.id.dingdan_shouhou:
               //跳转待售后界面
                Intent intent5 =  new Intent(getActivity(),ShouHou.class);
                intent5.putExtra("tuihuan","售后服务");
                startActivity(intent5);
                break;
        }
    }
}
