package hk.patsolution.animationentrydemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class MainActivity extends ActionBarActivity {
    private CircleMaskView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v=(CircleMaskView )findViewById(R.id.main_circle_mask_view);
    }


    protected void onResume(){
        super.onResume();
        BitmapFactory.Options opts=new BitmapFactory.Options();
        opts.inPreferredConfig= Bitmap.Config.RGB_565;
        Bitmap b=BitmapFactory.decodeResource(getResources(), R.drawable.demo,opts);
        v.setBitmap(b);
        v.startAnimation(2000);
    }
}
