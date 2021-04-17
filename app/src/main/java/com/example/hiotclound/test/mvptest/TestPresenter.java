package com.example.hiotclound.test.mvptest;

import android.webkit.JavascriptInterface;
import android.widget.TextView;

import com.example.hiotclound.base.BasePresenter;
import com.example.hiotclound.base.BaseView;
import com.example.hiotclound.test.dagger2test.ThirdObj;
import com.example.hiotclound.test.mvptest.model.User;

import javax.inject.Inject;

public class TestPresenter extends BasePresenter<TestView> {


    @Inject
    ThirdObj thirdObj;

    @Inject
    public TestPresenter() {
    }


    public void login(User user){
        thirdObj.thirdAction();
        if ("caiyu".equals(user.getUserName()) && "123".equals(user.getPassword())){
            getView().showMessage("登录成功");
        }
        else {
            getView().showMessage("登录失败");

        }
    }

}

