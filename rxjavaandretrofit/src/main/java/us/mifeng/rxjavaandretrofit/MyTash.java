package us.mifeng.rxjavaandretrofit;

/**
 * Created by shido on 2017/8/7.
 */

public abstract class MyTash implements Runnable {
    public FenLeiBean getBean() {
        return bean;
    }

    public void setBean(FenLeiBean bean) {
        this.bean = bean;
    }

    public FenLeiBean bean;



}
