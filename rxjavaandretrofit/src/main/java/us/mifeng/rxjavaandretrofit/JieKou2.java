package us.mifeng.rxjavaandretrofit;

import java.util.HashMap;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by shido on 2017/8/7.
 */

public interface JieKou2 {
    @GET("index.php?m=Api&c=Goods&a=goodsCategoryList")
    Observable<FenLeiBean> getCall(@QueryMap HashMap<String,String> map);
}
