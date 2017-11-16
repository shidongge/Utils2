package us.mifeng.utils.hmfrag;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.utils.R;
import us.mifeng.utils.adapter.RecycAdapter;
import us.mifeng.utils.adapter.XiHuanAdapter;
import us.mifeng.utils.bean.ADBean;
import us.mifeng.utils.bean.TuiJian_Hor;
import us.mifeng.utils.bean.XiHuanBean;
import us.mifeng.utils.http.WangZhi;
import us.mifeng.utils.ui.adactivity.ADActivity;
import us.mifeng.utils.utils.OkUtils;
import us.mifeng.utils.view.MyDecor;
import us.mifeng.utils.view.MyGridView;
import us.mifeng.utils.view.SwipeRefreshView;

/**
 * Created by shido on 2017/6/30.
 */


public class TuiJianFragment extends Fragment {
    private String url2 = "http://www.quanminlebang.com/api100/shanghu.php/";
    private String urlAD = "http://www.quanminlebang.com/api100/user.php/";
    private static final String TAG = "TuiJianFragment";
    private String pathUrl = "http://apis.juhe.cn/cook/query?key=e5f387cd3e868865aaaa894518c06491&menu=%E8%A5%BF%E7%BA%A2%E6%9F%BF&rn=10&";
    private SliderLayout slider;
    private HorizontalScrollView hor;
    private int index = 0;
    private List<XiHuanBean> list;
    private MyGridView mGv;
    private String tupian;
    private SwipeRefreshView mStr;
    private HashMap<String, String> map;
    private SwipeToLoadLayout swipeToLoadLayout;
    private List<XiHuanBean> list1;
    private RecyclerView recyclerView;
    private List<TuiJian_Hor> list_hor;
    private List<ADBean> list_AD;
    private ADBean adBean;
    private List<ADBean.ObjBean.ContentBean> content;
    protected boolean isVisible;
    private String xihuan_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View inflate = View.inflate(getActivity(), R.layout.tuijianfragment, null);
        slider = (SliderLayout) inflate.findViewById(R.id.slider);
        recyclerView = (RecyclerView) inflate.findViewById(R.id.tuijian_recycle);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(manager);

        mGv = (MyGridView) inflate.findViewById(R.id.gv);

        swipeToLoadLayout = (SwipeToLoadLayout) inflate.findViewById(R.id.swipeToLoadLayout);
        list = new ArrayList<XiHuanBean>();

        //推荐界面广告轮轮播
        initAD();
        //推荐界面横向滑动的recycleView
        initHor();

        //加载更多数据
        initUrl();
        //上拉加载，下拉刷新
        initLianWang();
        mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                XiHuanBean xiHuanBean = (XiHuanBean) parent.getItemAtPosition(position);

                String id1 = xiHuanBean.getId();
                Intent intent = new Intent(getActivity(), ADActivity.class);
                intent.putExtra("shanghuid",id1);
                startActivity(intent);
            }
        });
        return inflate;
    }

    private void initAD() {
        HashMap<String, String> map = new HashMap<>();
        map.put("bs", "appjiaodiantu");
        map.put("zuobiao", "116.125605,40.232183");
        OkUtils.UploadSJ(urlAD, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message message = hand.obtainMessage();
                message.obj = string;
                message.what = 300;
                hand.sendMessage(message);
            }
        });
    }

    private void initLianWang() {
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                //设置下拉刷新结束
                swipeToLoadLayout.setRefreshing(false);
            }
        });
        //为swipeToLoadLayout设置上拉加载更多监听者
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //设置上拉加载更多结束
                initUrl();
            }
        });
    }

    private void initHor() {
        list_hor = new ArrayList<>();
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("bs", "shoucangtuijian");
        map1.put("openid", "de2fcefdc93b947601fdad5408526914");
        OkUtils.UploadSJ(url2, map1, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message message = hand.obtainMessage();
                message.obj = string;
                message.what = 200;
                hand.sendMessage(message);
            }
        });
    }

    public void getMap() {
        map = new HashMap<>();
        map.put("openid", "de2fcefdc93b947601fdad5408526914");
        map.put("bs", "jingxuanshanghu");
        map.put("paixu", "0");
        map.put("zuobiao", "116.125405,40.232343");
    }

    private void initUrl() {
        getMap();
        index++;
        map.put("page", index + "");
        OkUtils.UploadSJ(url2, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                if (response.isSuccessful() && index > 1) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            swipeToLoadLayout.setLoadingMore(false);
                        }
                    });
                }
                Message mess = hand.obtainMessage();
                mess.obj = string;
                mess.what = 100;
                hand.sendMessage(mess);
            }
        });

    }

    Handler hand = new Handler() {
        private XiHuanAdapter adapter;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //第一次加载listview数据
            if (msg.what == 100) {
                String str = (String) msg.obj;
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(str);
                    String code = jsonObject.getString("code");
                    if ("200".equals(code)) {
                        JSONObject result = jsonObject.getJSONObject("obj");
                        JSONArray data = result.getJSONArray("list");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject jo = data.getJSONObject(i);
                            XiHuanBean bean = new XiHuanBean();
                            String jianjie = jo.getString("jianjie");
                            xihuan_id = jo.getString("id");
                            String title = jo.getString("mingcheng");
                            tupian = jo.getString("cover");
                            bean.setImgUrl(tupian);
                            bean.setJianjie(jianjie);
                            bean.setTitle(title);
                            bean.setId(xihuan_id);
                            list.add(bean);
                        }
                    }
                    adapter = new XiHuanAdapter(list, getActivity());
                    mGv.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            //横向滑动的recycleView
            if (msg.what == 200) {
                String str = (String) msg.obj;
                try {
                    JSONObject jo = new JSONObject(str);
                    String code = jo.getString("code");
                    if ("200".equals(code)) {
                        JSONObject obj = jo.getJSONObject("obj");
                        JSONArray list = obj.getJSONArray("list");
                        for (int i = 0; i < list.length(); i++) {
                            JSONObject jsonObject = list.getJSONObject(i);
                            TuiJian_Hor tuiJian_hor = new TuiJian_Hor();
                            String mingcheng = jsonObject.getString("mingcheng");
                            String tubiao = jsonObject.getString("tubiao");
                            tuiJian_hor.setMingcheng(mingcheng);
                            tuiJian_hor.setTubiao(tubiao);
                            list_hor.add(tuiJian_hor);
                        }
                        RecycAdapter recycAdapter = new RecycAdapter(list_hor, getActivity());
                        recyclerView.setAdapter(recycAdapter);
                        recyclerView.addItemDecoration(new MyDecor(getActivity(), RecyclerView.HORIZONTAL));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            //滚动的viewpager
            if (msg.what == 300) {
                String string = (String) msg.obj;
                Gson gson = new Gson();
                adBean = gson.fromJson(string, ADBean.class);
                int code = adBean.getCode();
                if (200 == code) {
                    content = adBean.getObj().getContent();
                    HashMap<String, String> url_maps = new HashMap();
                    for (int i = 0; i < content.size(); i++) {
                        String id = content.get(i).getId();
                        String pic = content.get(i).getPic();
                        String shanghuid = content.get(i).getShanghuid();
                        url_maps.put(content.get(i).getTitle(), WangZhi.HOME_AD + content.get(i).getPic());
                    }
                    //slider初始化
                    initList(url_maps);
                }
            }
        }
    };

    protected void initList(HashMap<String, String> url_maps) {
        slider.setPresetTransformer(SliderLayout.Transformer.Background2Foreground);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider.setCustomAnimation(new DescriptionAnimation());
        slider.setDuration(4000);

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            Intent intent = new Intent(getActivity(), ADActivity.class);
                            for (int i = 0; i < content.size(); i++) {
                                ADBean.ObjBean.ContentBean contentBean = content.get(i);
                                String url = slider.getUrl();
                                if (url.equals(WangZhi.HOME_AD + contentBean.getPic())) {
                                    intent.putExtra("shanghuid", contentBean.getShanghuid());
                                    startActivity(intent);
                                }
                            }
                        }
                    });

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
            slider.addSlider(textSliderView);
        }
    }

    @Override
    public void onStop() {
        slider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()){
            isVisible=true;
        }else {
            isVisible=false;
        }
    }
}
