package us.mifeng.mvvmdemo;

import android.app.Activity;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

import us.mifeng.mvvmdemo.databinding.ActivityMainBinding;

/**
 * Created by shido on 2017/10/9.
 */

public class VM1 {
    ActivityMainBinding amb;
    public VM1(Activity act,ActivityMainBinding amb){
        this.amb=amb;

    }
    public void click(View view){
        VM vm = new VM("呵呵", (float) Math.random() * 100f, "xxx");
        amb.setVm(vm);
    }


    public void setClick(){
        amb.setOnclick(this);
    }
    public void setName(){
        amb.setStr("小明");
    }
    public void setList(){
        ArrayList<String> list = new ArrayList<>();
        for (int i =0;i<10 ;i++){
            list.add("小明"+i);
        }
    }
    public void setMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("name","xiaoming");
        map.put("price","11111");
        map.put("desc","xxxx");
        amb.setMap(map);
    }

}
