package us.mifeng.mvvmdemo;

/**
 * Created by shido on 2017/10/9.
 */

public class B {
    public static B b;
    private B(){}
    public static synchronized B getb(){
        if (b ==null){
            b = new B();
        }
        return b;
    }
}
