package us.mifeng.rxjavaandretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    // String str [] = {"呵呵哒","xxx","mmm"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initEvent();//RxJava基础 用法
        //initEvent2();//RxJava中级 用法
        //initEvetn3();//RxJava高级 用法
        initRetrofit();
    }

    private void initRetrofit() {
        HttpHerper.getIntence().getUrl(new MyTash() {
            @Override
            public void run() {
                Log.e(TAG, "run: "+getBean().getMsg() );
            }
        });
    }


    private void initEvetn3() {
        Observable.just("呵呵哒")
                .subscribe(s -> Log.e(TAG, "initEvetn3: "+s));
    }

    private void initEvent2() {
        Observable<String> observable = Observable.just("呵呵哒");

        
    }

    private void initEvent() {
        //1.创建一个Observable对象，直接调用Observable.create方法
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
               RetrofitUtils.getInstance().getWaresHot1(new Callback<String>() {
                   @Override
                   public void onResponse(Call<String> call, Response<String> response) {
                       String body = response.body();
                       subscriber.onNext(body);
                   }

                   @Override
                   public void onFailure(Call<String> call, Throwable t) {

                   }
               });
                subscriber.onCompleted();
            }
        });
        //2.创建Subscriber来处理Observable对象发出的字符串
        Subscriber<String> subscriber = new Subscriber<String>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: "+s );
            }
        };
        //3.把Observable和Subscriber关联起来
        observable.subscribe(subscriber);
    }
}
