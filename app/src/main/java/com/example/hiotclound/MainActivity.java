package com.example.hiotclound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hiotclound.base.BaseActivity;
import com.example.hiotclound.base.BasePresenter;
import com.example.hiotclound.test.mvptest.model.User;
import com.example.hiotclound.utils.Constans;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置Viewpager
        final ViewPager vpMain = findViewById(R.id.vp_main);
        vpMain.setAdapter(new MainViewPagerAdapter());
        vpMain.setOffscreenPageLimit(Constans.MAIN_FRAGMENT_COUNT);

        RadioGroup rgMain = findViewById(R.id.rg_main);
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_message:
                        vpMain.setCurrentItem(Constans.MAIN_VIEWPAGER_INDEX_MAESSAGE);
                        break;
                    case R.id.rb_equipment:
                        vpMain.setCurrentItem(Constans.MAIN_VIEWPAGER_INDEX_EQUIPMENT);
                        break;
                    case R.id.rb_scene:
                        vpMain.setCurrentItem(Constans.MAIN_VIEWPAGER_INDEX_SCENE);
                        break;
                    case R.id.rb_mine:
                        vpMain.setCurrentItem(Constans.MAIN_VIEWPAGER_INDEX_MINE);
                        break;
                     default:





                }
            }
        });

    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }


}
