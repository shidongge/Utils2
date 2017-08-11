package us.mifeng.utils.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import us.mifeng.utils.R;
import us.mifeng.utils.fragment.BranchFragment;
import us.mifeng.utils.fragment.HomeFragment;
import us.mifeng.utils.fragment.MineFragment;
import us.mifeng.utils.fragment.ShopFragment;
import us.mifeng.utils.fragment.SpecialFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private static boolean mBackKeyPressed = false;//记录是否有首次按键

    private FragmentManager fm;
    private HomeFragment homefragment;
    private ImageView home_img,shop_img,mine_img,branch_img,special_img;
    private TextView home_tv,shop_tv,mine_tv,branch_tv,special_tv;
    private MineFragment mineFragment;
    private ShopFragment shopFragment;
    private BranchFragment branchFragment;
    private SpecialFragment specialFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homefragment = new HomeFragment();
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.ll_shang, homefragment);
        ft.commit();


        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        initView();
    }

    private void initView() {
        LinearLayout ll_home = (LinearLayout) findViewById(R.id.ll_home);
        LinearLayout ll_shop = (LinearLayout) findViewById(R.id.ll_shop);
        LinearLayout ll_mine = (LinearLayout) findViewById(R.id.ll_mine);
        LinearLayout ll_branch = (LinearLayout) findViewById(R.id.ll_branch);
        LinearLayout ll_special = (LinearLayout) findViewById(R.id.ll_special);

        home_img = (ImageView) findViewById(R.id.ll_home_img);
        shop_img = (ImageView) findViewById(R.id.ll_shop_img);
        mine_img = (ImageView) findViewById(R.id.ll_mine_img);
        branch_img = (ImageView) findViewById(R.id.ll_branch_img);
        special_img = (ImageView) findViewById(R.id.ll_special_img);

        home_tv = (TextView) findViewById(R.id.ll_home_tv);
        shop_tv = (TextView) findViewById(R.id.ll_shop_tv);
        mine_tv = (TextView) findViewById(R.id.ll_mine_tv);
        branch_tv = (TextView) findViewById(R.id.ll_branch_tv);
        special_tv = (TextView) findViewById(R.id.ll_special_tv);

        ll_home.setOnClickListener(this);
        ll_mine.setOnClickListener(this);
        ll_branch.setOnClickListener(this);
        ll_shop.setOnClickListener(this);
        ll_special.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        hintFragment();
        setText();
        FragmentTransaction ft = fm.beginTransaction();
        switch (v.getId()){
            case R.id.ll_home:
                home_img.setImageResource(R.mipmap.mainpage_home_pressed_ic);
                home_tv.setTextColor(Color.parseColor("#ff0000"));
                ft.show(homefragment);
                break;
            case R.id.ll_special:
                special_img.setImageResource(R.mipmap.mainpage_topic_pressed_ic);
                special_tv.setTextColor(Color.parseColor("#ff0000"));
                if (specialFragment==null){
                    specialFragment = new SpecialFragment();
                    ft.add(R.id.ll_shang,specialFragment);
                }else {
                    ft.show(specialFragment);
                }
                break;
            case R.id.ll_branch:
                branch_img.setImageResource(R.mipmap.mainpage_category_pressed_ic);
                branch_tv.setTextColor(Color.parseColor("#ff0000"));
                if (branchFragment==null){
                    branchFragment = new BranchFragment();
                    ft.add(R.id.ll_shang,branchFragment);
                }else {
                    ft.show(branchFragment);
                }
                break;
            case R.id.ll_shop:
                shop_img.setImageResource(R.mipmap.all_shopping_cart_pressed_ic);
                shop_tv.setTextColor(Color.parseColor("#ff0000"));
                if (shopFragment==null){
                    shopFragment = new ShopFragment();
                    ft.add(R.id.ll_shang,shopFragment);
                }else {
                    ft.show(shopFragment);
                }
                break;
            case R.id.ll_mine:
                mine_img.setImageResource(R.mipmap.mainpage_person_pressed_ic);
                mine_tv.setTextColor(Color.parseColor("#ff0000"));
                if (mineFragment==null){
                    mineFragment = new MineFragment();
                    ft.add(R.id.ll_shang,mineFragment);
                }else {
                    ft.show(mineFragment);
                }
                break;
        }
        ft.commit();
    }
    public void hintFragment(){
        FragmentTransaction ft = fm.beginTransaction();
        if (homefragment!=null){
            ft.hide(homefragment);
        }
        if (mineFragment !=null){
            ft.hide(mineFragment);
        }
        if (shopFragment !=null){
            ft.hide(shopFragment);
        }
        if (branchFragment !=null){
            ft.hide(branchFragment);
        }
        if (specialFragment !=null){
            ft.hide(specialFragment);
        }
        ft.commit();
    }
    public void setText(){
        home_tv.setTextColor(Color.parseColor("#000000"));
        mine_tv.setTextColor(Color.parseColor("#000000"));
        shop_tv.setTextColor(Color.parseColor("#000000"));
        special_tv.setTextColor(Color.parseColor("#000000"));
        branch_tv.setTextColor(Color.parseColor("#000000"));

        home_img.setImageResource(R.mipmap.mainpage_home_nor_ic);
        mine_img.setImageResource(R.mipmap.mainpage_person_nor_ic);
        branch_img.setImageResource(R.mipmap.mainpage_category_nor_ic);
        shop_img.setImageResource(R.mipmap.all_shopping_cart_nor_ic);
        special_img.setImageResource(R.mipmap.mainpage_topic_nor_ic);
    }

    @Override
    public void onBackPressed() {
        if(!mBackKeyPressed) {
            Toast.makeText(MainActivity.this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            },2000);

        }else {
            //退出程序
            this.finish();
            System.exit(0);
        }

    }
}
