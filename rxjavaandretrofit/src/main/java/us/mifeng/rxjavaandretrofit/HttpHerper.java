package us.mifeng.rxjavaandretrofit;

import android.os.Handler;
import android.os.Looper;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by shido on 2017/8/7.
 */

public class HttpHerper {
    private static HttpHerper httpHerper;
    private final JieKou2 jieKou;
    private final Retrofit retrofit;

    private HttpHerper(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://shop.sdlinwang.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        jieKou = retrofit.create(JieKou2.class);
    }
    static HttpHerper getIntence(){
        if (httpHerper==null){
            synchronized (HttpHerper.class){
                if (httpHerper==null){
                    httpHerper = new HttpHerper();
                }
            }
        }
        return httpHerper;
    }
    public void getUrl(MyTash myTash){
        HashMap<String, String> map = new HashMap<>();
        Observable<FenLeiBean> call = jieKou.getCall(map);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<FenLeiBean>() {
                    @Override
                    public void call(FenLeiBean s) {
                        Handler handler = new Handler(Looper.myLooper());
                        myTash.setBean(s);
                        handler.post(myTash);
                    }
                });
    }
}
