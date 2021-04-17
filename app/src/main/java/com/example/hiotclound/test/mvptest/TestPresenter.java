package com.example.hiotclound.test.mvptest;

import android.widget.TextView;

import com.example.hiotclound.base.BasePresenter;
import com.example.hiotclound.base.BaseView;
import com.example.hiotclound.test.mvptest.model.User;

public class TestPresenter extends BasePresenter<TestView> {

    public TestPresenter() {
    }


    public void login(User user){
        if ("caiyu".equals(user.getUserName()) && "123".equals(user.getPassword())){
            getView().showMessage("登录成功");
        }
        else {
            getView().showMessage("登录失败");

        }
    }

}

