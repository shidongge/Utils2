package us.mifeng.utils.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import us.mifeng.utils.R;

/**
 * Created by shido on 2017/9/15.
 */

public class DianPu extends Activity implements View.OnClickListener {
    //http://www.quanminlebang.com/m/mobile/mobilestation/shopinfo.html?src=app&id=4651
    private WebView wv;
    private static final String TAG = "DianPu";
    private String shanghuid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dianpu);
        shanghuid = getIntent().getStringExtra("shanghuid");
        Log.e(TAG, "initView: " + shanghuid);
        wv = (WebView) findViewById(R.id.dp_wv);
        ImageView back = (ImageView) findViewById(R.id.dp_back);
        back.setOnClickListener(this);
        initView();
    }


    private void initView() {
        //启用支持javascript
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        wv.loadUrl("http://www.quanminlebang.com/m/mobile/mobilestation/shopinfo.html?src=app&id="+shanghuid);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dp_back:
                finish();
                break;
        }
    }
}
