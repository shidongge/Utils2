package us.mifeng.anjian;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        doSpecialiSomethingAsVirtualBar();
    }

    private void initView() {
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
       //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }
    private void doSpecialiSomethingAsVirtualBar(){

        KeyBoardListenerManager.newInstance(this).init();
        if (PhoneSystemManager.AndroidWorkaround.checkDeviceHasNavigationBar(this)) {
            PhoneSystemManager.AndroidWorkaround.assistActivity(findViewById(android.R.id.content));
            ViewStub stub = (ViewStub) findViewById(R.id.view_stub);
            stub.inflate();
            View enuiStubView = this.findViewById(R.id.enuiNatView);
            LinearLayout.LayoutParams zLayoutParams = (LinearLayout.LayoutParams) enuiStubView.getLayoutParams();
            zLayoutParams.height = PhoneSystemManager.AndroidWorkaround.getVirtualBarHeigh(this);
            enuiStubView.setLayoutParams(zLayoutParams);
        }
    }
}
