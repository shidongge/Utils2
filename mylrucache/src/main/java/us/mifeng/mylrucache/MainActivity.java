package us.mifeng.mylrucache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private DiskLruCache mDiskLruCache;
    private Context mContext;
    //DiskLruCache中对于图片的最大缓存值.
    private int maxSize=20*1024*1024;
    private String mImagePath="http://www.baidu.com/img/bdlogo.png";
    private Handler mHandler;
    private final int FINISH=9527;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        try {
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (msg.what==FINISH) {
                        getDataFromDiskLruCache();
                    }
                }
            };
            mContext=this;
            mImageView=(ImageView) findViewById(R.id.imageView);

            initDiskLruCache();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    /**
     * 初始化DiskLruCache
     */
    private void initDiskLruCache(){
        try {
            File cacheDir=Utils.getDiskLruCacheDir(mContext, bitmap);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            int versionCode=Utils.getAppVersionCode(mContext);
            mDiskLruCache=DiskLruCache.open(cacheDir, versionCode, 1, maxSize);

            saveDataToDiskLruCache();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    // 将数据写入DiskLruCache
    private void saveDataToDiskLruCache() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //第一步:获取将要缓存的图片的对应唯一key值.
                    String key = Utils.getStringByMD5(mImagePath);
                    //第二步:获取DiskLruCache的Editor
                    DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                    if (editor!=null) {
                        //第三步:从Editor中获取OutputStream
                        OutputStream outputStream=editor.newOutputStream(0);
                        //第四步:下载网络图片且保存至DiskLruCache图片缓存中
                        boolean isSuccessfull=Utils.getBitmapFromNetWorkAndSaveToDiskLruCache(mImagePath, outputStream);
                        if (isSuccessfull) {
                            editor.commit();
                            mHandler.sendEmptyMessage(FINISH);
                        }else{
                            editor.abort();
                        }
                        mDiskLruCache.flush();
                    }
                } catch (Exception e) {

                }

            }
        }).start();
    }

    //从DiskLruCache中读取数据
    private void getDataFromDiskLruCache(){
        try {
            //第一步:获取已缓存的图片的对应唯一key值.
            String key = Utils.getStringByMD5(mImagePath);
            //第二步:依据key获取到其对应的snapshot
            DiskLruCache.Snapshot snapshot=mDiskLruCache.get(key);
            if (snapshot!=null) {
                //第三步:从snapshot中获取到InputStream
                InputStream inputStream=snapshot.getInputStream(0);
                bitmap = BitmapFactory.decodeStream(inputStream);
                mImageView.setImageBitmap(bitmap);
            }
        } catch (Exception e) {

        }
    }
}
