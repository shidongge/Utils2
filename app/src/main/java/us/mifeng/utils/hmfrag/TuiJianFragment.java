package us.mifeng.utils.hmfrag;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

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
import us.mifeng.utils.adapter.XiHuanAdapter;
import us.mifeng.utils.bean.XiHuanBean;
import us.mifeng.utils.utils.OkUtils;
import us.mifeng.utils.utils.ToSi;
import us.mifeng.utils.view.MyGridView;
import us.mifeng.utils.view.SwipeRefreshView;

/**
 * Created by shido on 2017/6/30.
 */


public class TuiJianFragment extends Fragment {
    private static final String TAG = "TuiJianFragment";
    private String pathUrl = "http://apis.juhe.cn/cook/query?key=e5f387cd3e868865aaaa894518c06491&menu=%E8%A5%BF%E7%BA%A2%E6%9F%BF&rn=10&";
    private SliderLayout slider;
    private HorizontalScrollView hor;
    private int index = 1;
    private List<XiHuanBean> list;
    private MyGridView mGv;
    private String tupian;
    private SwipeRefreshView mStr;
    private HashMap<String, String> map;
    private SwipeToLoadLayout swipeToLoadLayout;
    private List<XiHuanBean> list1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View inflate = View.inflate(getActivity(), R.layout.tuijianfragment, null);
        slider = (SliderLayout) inflate.findViewById(R.id.slider);
        // mStr = (SwipeRefreshView) inflate.findViewById(R.id.mStr);
        mGv = (MyGridView) inflate.findViewById(R.id.gv);
        hor = (HorizontalScrollView) inflate.findViewById(R.id.hor);
        swipeToLoadLayout = (SwipeToLoadLayout) inflate.findViewById(R.id.swipeToLoadLayout);
        list = new ArrayList<XiHuanBean>();
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
                swipeToLoadLayout.setLoadingMore(false);
                initUrl();
            }
        });
        //  mStr.setOnRefreshListener(this);
        //为SwipeRefreshLayout设置刷新时的颜色变化，最多可以设置4种
       /* mStr.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);*/
        initList();
        initUrl();
        return inflate;
    }

    private void initUrl() {
        map = new HashMap<>();
        index++;
        map.put("pn", index + "");
        if (index<6){
            OkUtils.UploadSJ(pathUrl, map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    Message mess = hand.obtainMessage();
                    mess.obj = string;
                    mess.what = 100;
                    hand.sendMessage(mess);

                }
            });
        }else {
            ToSi.show(getActivity(),"没有更多数据了");
        }
    }

    Handler hand = new Handler() {

        private XiHuanAdapter adapter;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                String str = (String) msg.obj;
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(str);
                    JSONObject result = jsonObject.getJSONObject("result");
                    JSONArray data = result.getJSONArray("data");

                    Log.e(TAG, "handleMessage: data= "+data.length() );
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject jo = data.getJSONObject(i);
                        XiHuanBean bean = new XiHuanBean();
                        String id = jo.getString("id");
                        String title = jo.getString("title");
                        JSONArray albums = jo.getJSONArray("albums");
                        for (int j = 0; j < albums.length(); j++) {
                            tupian = albums.getString(j);
                        }
                        bean.setImgUrl(tupian);
                        bean.setId(id);
                        bean.setTitle(title);
                        list.add(bean);
                    }
                    Log.e(TAG, "handleMessage: list= "+list.size() );
                    adapter = new XiHuanAdapter(list, getActivity());
                    mGv.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (msg.what == 200) {
                String str = (String) msg.obj;
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(str);
                    JSONObject result = jsonObject.getJSONObject("result");
                    JSONArray data = result.getJSONArray("data");
                    list = new ArrayList<XiHuanBean>();
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject jo = data.getJSONObject(i);
                        XiHuanBean bean = new XiHuanBean();
                        String id = jo.getString("id");
                        String title = jo.getString("title");
                        JSONArray albums = jo.getJSONArray("albums");
                        for (int j = 0; j < albums.length(); j++) {
                            tupian = albums.getString(j);
                        }
                        bean.setImgUrl(tupian);
                        bean.setId(id);
                        bean.setTitle(title);
                        list.add(bean);
                    }

                    if (adapter == null) {
                        XiHuanAdapter adapter = new XiHuanAdapter(list, getActivity());
                        mGv.setAdapter(adapter);
                    } else {
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    protected void initList() {
        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

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
                            //Toast.makeText(getActivity(),slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
                        }
                    });

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
            slider.addSlider(textSliderView);
        }

        hor.requestDisallowInterceptTouchEvent(true);

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

    /*@Override
    public void onRefresh() {
        //刷新
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //结束后停止刷新
                mStr.setRefreshing(false);
                ToSi.show(getActivity(), "我就是来搞笑的");
            }
        }, 3000);
    }*/

}
