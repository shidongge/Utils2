package us.mifeng.rxjavaandretrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shido on 2017/8/7.
 */

public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;
    private static JieKou jieKou;
    private static Retrofit retrofit;

    private RetrofitUtils(){
        jieKou = retrofit.create(JieKou.class);
    }
    static {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://shop.sdlinwang.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitUtils = new RetrofitUtils();
    }
    public void getWaresHot1(Callback<String> callback){
        Call<String> call = jieKou.getCall("");
        call.enqueue(callback);
    }

    public static RetrofitUtils getInstance(){
        return retrofitUtils;

    }
}
