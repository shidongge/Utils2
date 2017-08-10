package us.mifeng.utils.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by shido on 2017/6/28.
 */

public class SharedUtils {
    public String name = "tag";
    public void saveShared(Context ctx ,String key,String value){
        SharedPreferences sp = ctx.getSharedPreferences(name, 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key,value);
        edit.commit();
    }
    public String getShared(Context ctx,String key){
        String str = null;
        SharedPreferences sp = ctx.getSharedPreferences(name, 0);
        str = sp.getString(key, "");
        return str;
    }


}
