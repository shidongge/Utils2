package us.mifeng.feescodemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SimpleDraweeView img = (SimpleDraweeView) findViewById(R.id.Img);
        Uri parse = Uri.parse("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2659859240,2431702696&fm=27&gp=0.jpg");
        img.setImageURI(parse);

    }
}
