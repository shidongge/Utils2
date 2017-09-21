package us.mifeng.utils.ui.adactivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.utils.R;
import us.mifeng.utils.base.BaseActivity;
import us.mifeng.utils.http.WangZhi;
import us.mifeng.utils.ui.DianPu;
import us.mifeng.utils.utils.OkUtils;
import us.mifeng.utils.utils.TongMing;
import xu.viewpagerflextitle.MyPagerAdapter;
import xu.viewpagerflextitle.ViewPagerTitle;

/**
 * Created by shido on 2017/9/8.
 */

public class ADActivity extends BaseActivity implements View.OnClickListener {
    private ArrayList<View> list_view;
    //http://www.quanminlebang.com/api100/shanghu.php?bs=shanghuxiangqing&openid=de2fcefdc93b947601fdad5408526914&shanghuid=4594&zuobiao=116.125616,40.232183
    private ViewPager pager;
    private ImageView ad, back, shoucang, fenxiang, ad_phone;
    private String shanghuids;
    private String url = "http://www.quanminlebang.com/api100/shanghu.php?";
    private RelativeLayout rela,ad_pl_rl,ad_ry_rl,ad_fw_rl;
    private TextView title, ad_kaishishijian, ad_jieshushijian, ad_dizhi;
    private String dianhua;
    private ViewPagerTitle pagerTitle;
    private View fuwu_lv,renyaun_lv,pingjia_lv;
    private ListView ad_list_fw,ad_list_ry,ad_list_pj;
    private String zongtiaoshu;
    private RelativeLayout ad_rl;
    private LinearLayout ad_ll;

    @Override
    protected void initView() {

        list_view = new ArrayList<>();

        setContentView(R.layout.adacitivty);
        shanghuids = getIntent().getStringExtra("shanghuid");

        ad = (ImageView) findViewById(R.id.adactivity__ad);
        rela = (RelativeLayout) findViewById(R.id.ad_rela);
        ad_rl = (RelativeLayout) findViewById(R.id.ad_rl);
        ad_ll = (LinearLayout) findViewById(R.id.ad_ll);
        back = (ImageView) findViewById(R.id.adactivity_back);
        fenxiang = (ImageView) findViewById(R.id.adactivity_fenxiang);
        shoucang = (ImageView) findViewById(R.id.adactivity__shoucang);
        ad_phone = (ImageView) findViewById(R.id.ad_phone);
        title = (TextView) findViewById(R.id.ad_title);
        ad_kaishishijian = (TextView) findViewById(R.id.ad_yingyekaishi);
        ad_jieshushijian = (TextView) findViewById(R.id.ad_jieshushijian);
        ad_dizhi = (TextView) findViewById(R.id.ad_dizhi);


        pager = (ViewPager) findViewById(R.id.view_pager);
        pagerTitle = (ViewPagerTitle) findViewById(R.id.pager_title);
        pagerTitle.initData(new String[]{"服务项目", "服务人员", "评价列表"}, pager, 0);

        LayoutInflater inflater = getLayoutInflater();
        fuwu_lv = inflater.inflate(R.layout.ad_lv_fuwu, null);
        ad_fw_rl = (RelativeLayout) fuwu_lv.findViewById(R.id.ad_null);
        ad_list_fw = (ListView) fuwu_lv.findViewById(R.id.ad_list_fuwu);
        renyaun_lv = inflater.inflate(R.layout.ad_lv_ry,null);
        ad_ry_rl = (RelativeLayout) renyaun_lv.findViewById(R.id.ad_null);
        ad_list_ry = (ListView) renyaun_lv.findViewById(R.id.ad_list_renyuan);
        pingjia_lv = inflater.inflate(R.layout.ad_lv_pl,null);
        ad_pl_rl = (RelativeLayout) pingjia_lv.findViewById(R.id.ad_null);
        ad_list_pj = (ListView) pingjia_lv.findViewById(R.id.ad_list_pingjia);
        list_view.add(fuwu_lv);
        list_view.add(renyaun_lv);
        list_view.add(pingjia_lv);
        MyPagerAdapter adapter = new MyPagerAdapter(list_view);
        pager.setAdapter(adapter);
        back.setOnClickListener(this);
        TongMing.TongMing(ADActivity.this);
        ad_phone.setOnClickListener(this);
        fenxiang.setOnClickListener(this);
        ad_ll.setOnClickListener(this);
        ad_rl.setOnClickListener(this);
        //服务列表
        initAD_XM();
        //人员列表
        initAD_RY();
        //评价列表
        initAD_PG();
    }

    private void initAD_PG() {
        //http://www.quanminlebang.com/api100/shanghu.php?bs=shanghupinglun&shanghuid=4651&page=1
        HashMap<String,String> map = new HashMap<>();
        map.put("bs","shanghupinglun");
        map.put("shanghuid",shanghuids);
        map.put("page","1");
        OkUtils.UploadSJ(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message message = handler.obtainMessage();
                message.what=400;
                message.obj=string;
                handler.sendMessage(message);
            }
        });
    }

    private void initAD_RY() {
        //http://www.quanminlebang.com/api100/shanghu.php?bs=shanghuyuangongliebiao&shanghuid=4651&page=1
        HashMap map = new HashMap<>();
        map.put("bs","shanghuyuangongliebiao");
        map.put("shanghuid",shanghuids);
        map.put("page","1");
        OkUtils.UploadSJ(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message message = handler.obtainMessage();
                message.obj=string;
                message.what=300;
                handler.sendMessage(message);
            }
        });
    }

    //list服务项目列表
    private void initAD_XM() {
        //http://www.quanminlebang.com/api100/shanghu.php?bs=shanghufuwuliebiao&shanghuid=2852&page=1
        HashMap<String,String> map = new HashMap<>();
        map.put("bs","shanghufuwuliebiao");
        map.put("shanghuid",shanghuids);
        map.put("page","1");
        OkUtils.UploadSJ(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message mess = handler.obtainMessage();
                mess.obj=string;
                mess.what=500;
                handler.sendMessage(mess);
            }
        });
    }

    //爱家美食
    @Override
    protected void initList() {
        HashMap<String, String> map = new HashMap<>();
        map.put("bs", "shanghuxiangqing");
        map.put("openid", "de2fcefdc93b947601fdad5408526914");
        map.put("shanghuid", shanghuids);
        map.put("zuobiao", "116.125616,40.232183");
        OkUtils.UploadSJ(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message mess = handler.obtainMessage();
                mess.obj = string;
                mess.what = 200;
                handler.sendMessage(mess);
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //上部分的展示
            if (msg.what == 200) {
                String string = (String) msg.obj;
                Gson gson = new Gson();
                AD_Img_Bean adBean = gson.fromJson(string, AD_Img_Bean.class);
                String cover = adBean.getObj().getDianpu().getCover();
                String dizhi = adBean.getObj().getDianpu().getDizhi();
                String mingcheng = adBean.getObj().getDianpu().getMingcheng();
                String kaishishijian = adBean.getObj().getDianpu().getKaishishijian();
                String jieshushijian = adBean.getObj().getDianpu().getJieshushijian();
                dianhua = adBean.getObj().getDianpu().getDianhua();

                Glide.with(ADActivity.this).load(WangZhi.TUIJIAN_HOR + cover).into(ad);
                title.setText(mingcheng);
                ad_dizhi.setText(dizhi);
                ad_kaishishijian.setText(kaishishijian);
                ad_jieshushijian.setText(jieshushijian);

            }

            //服务项目列表
            if(msg.what==500){
                String string = (String) msg.obj;
                Gson gson = new Gson();
                AD_XM_Bean ad_xm_lv = gson.fromJson(string, AD_XM_Bean.class);
                int code = ad_xm_lv.getCode();
                if (200==code){
                    String zongtiaoshu = ad_xm_lv.getObj().getZongtiaoshu();
                    int  integer = Integer.valueOf(zongtiaoshu);
                    if (0==integer){
                        ad_fw_rl.setVisibility(View.VISIBLE);
                        ad_list_fw.setVisibility(View.GONE);
                    }else {
                        ad_fw_rl.setVisibility(View.GONE);
                        ad_list_fw.setVisibility(View.VISIBLE);
                       List<AD_XM_Bean.ObjBean.ListBean> list = ad_xm_lv.getObj().getList();
                        AD_XM_Adapter ad_xm_lv_adapter = new AD_XM_Adapter(list, ADActivity.this);
                        ad_list_fw.setAdapter(ad_xm_lv_adapter);
                    }
                }
            }
            //服务人员列表
            if (msg.what==300){
                String string = (String) msg.obj;
                Gson gson = new Gson();
                AD_RY_Bean ad_ry_bean = gson.fromJson(string, AD_RY_Bean.class);

                int code = ad_ry_bean.getCode();
                if (200==code){
                    int zongtiaoshu = ad_ry_bean.getObj().getZongtiaoshu();
                    if (0==zongtiaoshu){
                        ad_ry_rl.setVisibility(View.VISIBLE);
                        ad_list_ry.setVisibility(View.GONE);
                    }else {
                        ad_ry_rl.setVisibility(View.GONE);
                        ad_list_ry.setVisibility(View.VISIBLE);
                        List<AD_RY_Bean.ObjBean.ListBean> list = ad_ry_bean.getObj().getList();
                        AD_RY_Adapter ad_ry_adapter = new AD_RY_Adapter(ADActivity.this,list);
                        ad_list_ry.setAdapter(ad_ry_adapter);
                    }

                }
            }
            //评论列表
            if (msg.what==400){
                String string = (String) msg.obj;
                Gson gson = new Gson();
                AD_PL_Bean ad_pl_bean = gson.fromJson(string, AD_PL_Bean.class);
                zongtiaoshu = ad_pl_bean.getObj().getZongtiaoshu();
                if ("0".equals(zongtiaoshu)){
                    ad_pl_rl.setVisibility(View.VISIBLE);
                    ad_list_pj.setVisibility(View.GONE);
                }else {
                    ad_pl_rl.setVisibility(View.GONE);
                    ad_list_pj.setVisibility(View.VISIBLE);
                    List<AD_PL_Bean.ObjBean.ListBean> list = ad_pl_bean.getObj().getList();
                    AD_PL_Adapter ad_pl_adapter = new AD_PL_Adapter(ADActivity.this, list);
                    ad_list_pj.setAdapter(ad_pl_adapter);
                }

            }


        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adactivity_back:
                finish();
                break;
            case R.id.ad_phone:
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri parse = Uri.parse("tel:" + dianhua);
                intent.setData(parse);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
                break;
            case R.id.adactivity_fenxiang:
                //分享
                showShare();
                break;
            case R.id.ad_ll:
                Intent intent1 = new Intent(ADActivity.this, DianPu.class);
                intent1.putExtra("shanghuid",shanghuids);
                startActivity(intent1);
                break;
            case R.id.ad_rl:
                Intent intent2 = new Intent(ADActivity.this, DianPu.class);
                intent2.putExtra("shanghuid",shanghuids);
                startActivity(intent2);
                break;
        }
    }


    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("我是分享");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }
}
