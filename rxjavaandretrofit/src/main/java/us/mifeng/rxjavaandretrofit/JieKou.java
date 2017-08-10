package us.mifeng.rxjavaandretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shido on 2017/8/7.
 */

public interface JieKou {
    @GET("index.php?m=Api&c=Goods&a=goodsCategoryList")
    Call<String> getCall(@Query("id") String string);
}
