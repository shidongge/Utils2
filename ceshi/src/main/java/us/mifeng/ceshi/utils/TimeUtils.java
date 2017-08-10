package us.mifeng.ceshi.utils;


import android.icu.util.Calendar;
import android.text.format.Time;

/**
 * Created by shido on 2017/7/13.
 */

public class TimeUtils {
    public int time(int num){
        if (1== Calendar.MONTH
                ||3==Calendar.MONTH
                ||5==Calendar.MONTH
                ||7==Calendar.MONTH
                ||8==Calendar.MONTH
                ||10==Calendar.MONTH
                ||12==Calendar.MONTH){
            num = 31;
            return num;
        }else if (4==Calendar.MONTH
                ||6==Calendar.MONTH
                ||9==Calendar.MONTH
                ||11==Calendar.MONTH){
            num = 30;
            return num;
        }else if (2==Calendar.MONTH){
            if (Calendar.YEAR%100==0){
                if (Calendar.YEAR%4==0){
                    num = 29;
                }
            }
        }


        return num;
    }
}
