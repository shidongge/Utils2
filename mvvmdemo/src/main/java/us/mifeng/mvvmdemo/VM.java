package us.mifeng.mvvmdemo;

import android.databinding.ObservableField;

/**
 * Created by shido on 2017/10/9.
 */

public class VM {
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<Float> price = new ObservableField<>();
    public ObservableField<String> desc = new ObservableField<>();
    public VM(String name,float price,String desc){
        this.desc.set(desc);
        this.name.set(name);
        this.price.set(price);
    }
}
