package us.mifeng.utils.fragment;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
import us.mifeng.utils.adapter.BranchAdapter;
import us.mifeng.utils.base.BaseFragment;
import us.mifeng.utils.utils.OkUtils;

/**
 * Created by shido on 2017/6/28.
 */

/**
 * 这是分类 fragment
 */
public class BranchFragment extends BaseFragment {
    private List<String> list2 = new ArrayList<>();
    private List<String> list1 = new ArrayList<>();
    private String[] str = {
            "推荐区","夏凉区","家装区","居家","餐厨","配件","服装","洗护","婴童","杂货","饮食","志趣"
    };
    private ListView mLv;
    private List<String> list;
    private TextView mTv;
    private BranchAdapter branchAdapter;
    private FrameFragment frameFragment;
    private static  int mPosition;
    private FragmentManager fm;
    private ListView rifht_lv;
    private String url = "http://shop.sdlinwang.com/index.php?m=Api&c=Goods&a=goodsCategoryList";
    private JSONArray result;
    private MyAdapter myAdapter;


    @Override
    protected View initView() {
        View inflate = View.inflate(getActivity(), R.layout.branch_fragment, null);
        mLv = (ListView) inflate.findViewById(R.id.mLv);
        rifht_lv = (ListView) inflate.findViewById(R.id.right_lv);
        //initReplace();
        //initEvent();
        initUrl();
        initListView();

        return inflate;
    }

    private void initUrl() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> map = new HashMap<>();
                map.put("","");
                OkUtils.UploadSJ(url, map, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        Message mess = hand.obtainMessage();
                        mess.obj=string;
                            mess.what=200;
                            hand.sendMessage(mess);
                    }
                });
            }
        }).start();
    }
    Handler hand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==200){
                final String json  = (String) msg.obj;
                try {
                    final JSONObject jo = new JSONObject(json);
                    String status = jo.getString("status");
                    if ("1".equals(status)){
                        result = jo.getJSONArray("result");
                        for (int i =0;i<result.length();i++){
                            JSONObject jsonObject = result.getJSONObject(i);
                            String name = jsonObject.getString("name");
                            list1.add(name);
                        }
                        JSONObject jsonObject = result.getJSONObject(0);
                        JSONArray twoCategory = jsonObject.getJSONArray("twoCategory");
                        for (int z= 0;z<twoCategory.length();z++){
                            JSONObject two = twoCategory.getJSONObject(z);
                            String name1 = two.getString("name");
                            list2.add(name1);

                        }
                            branchAdapter = new BranchAdapter(list2, getActivity());
                            rifht_lv.setAdapter(branchAdapter);
                        }
                    myAdapter = new MyAdapter(list1);
                    mLv.setAdapter(myAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private void initListView() {
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                list2.clear();
                myAdapter.setSelectedPosition(position);
                myAdapter.notifyDataSetChanged();
                try {
                    JSONObject jsonArray = result.getJSONObject(position);
                    String name = jsonArray.getString("name");
                    JSONArray twoCategory = jsonArray.getJSONArray("twoCategory");
                    for (int z= 0;z<twoCategory.length();z++){
                        JSONObject two = twoCategory.getJSONObject(z);
                        String name1 = two.getString("name");
                        list2.add(name1);

                    }
                    branchAdapter = new BranchAdapter(list2, getActivity());
                    rifht_lv.setAdapter(branchAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        rifht_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
            }
        });
    }
    class MyAdapter extends BaseAdapter{
        private List<String> list3;
        private int position2 = 0;
        public MyAdapter(List<String> list3){
            this.list3=list3;
        }
        @Override
        public int getCount() {
            return list3.size();
        }

        @Override
        public Object getItem(int position) {
            return list3.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        public void setSelectedPosition(int position) {
            this.position2 = position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHorder vh =null;
            if (vh==null){
                vh = new ViewHorder();
                convertView = View.inflate(getActivity(),R.layout.branch_iten,null);
                vh.tv = (TextView) convertView.findViewById(R.id.btanch_tv);
                convertView.setTag(vh);
            }else {
                vh = (ViewHorder) convertView.getTag();
            }

            vh.tv.setText(list3.get(position));
            if (position==position2){
                vh.tv.setBackgroundColor(Color.WHITE);
            }else {
                vh.tv.setBackgroundColor(0xEEEEEE);
            }
            return convertView;
        }
        class ViewHorder{
            TextView tv;
        }

    }
//    private void initReplace() {
//        frameFragment = new FrameFragment();
//        fm = getFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.frame,frameFragment);
//
//        //通过bundle传值给fragment
//        Bundle bundle = new Bundle();
//        bundle.putString(FrameFragment.TAG,str[mPosition]);
//        frameFragment.setArguments(bundle);
//        ft.commit();
//    }

//    private void initEvent() {
//        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                FragmentTransaction ft = fm.beginTransaction();
//                if (frameFragment!=null){
//                    ft.hide(frameFragment);
//                }
//               //得到当前位置
//                mPosition = position;
//                //及时刷新适配器
//                branchAdapter.notifyDataSetChanged();
//                for (int i = 0 ; i<str.length;i++){
//                    fm = getFragmentManager();
//                    ft = fm.beginTransaction();
//                    ft.replace(R.id.frame,frameFragment)
//                    //通过bundle传值给fragment
//                    Bundle bundle = new Bundle();
//                    bundle.putString(FrameFragment.TAG,str[mPosition]);
//                    frameFragment.setArguments(bundle);
//                    ft.commit();
//
//                }
//            }
//        });‘


//    }

    @Override
    protected void initList() {
//        list = new ArrayList<>();
//        for (int i = 0;i<str.length;i++){
//            list.add(str[i]);
//        }
//        branchAdapter = new BranchAdapter(list,getActivity());
//        mLv.setAdapter(branchAdapter);
    }


}
