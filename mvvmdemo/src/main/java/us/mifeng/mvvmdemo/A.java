package us.mifeng.mvvmdemo;

/**
 * Created by shido on 2017/10/9.
 */

public class A {
    //恶汉式
    public static A a = new A();
    private A(){

    }
    public static A getInstance(){
        return a;
    }

}
