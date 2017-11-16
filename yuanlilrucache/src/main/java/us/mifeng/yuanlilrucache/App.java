package us.mifeng.yuanlilrucache;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by shido on 2017/8/10.
 */

public class App extends Application {

    public static int versionCode;

    @Override
    public void onCreate() {
        super.onCreate();
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            versionCode = packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
