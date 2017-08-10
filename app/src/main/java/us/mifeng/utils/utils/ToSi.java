package us.mifeng.utils.utils;

import android.content.Context;
import android.widget.Toast;

public class ToSi {
	static String st=null;

	private static Toast to;
	public static void show(Context context,String str){
		if(!str.equals(st)){
			st=str;
			to = Toast.makeText(context,str, Toast.LENGTH_SHORT);
		}
		to.show();
	}

}
