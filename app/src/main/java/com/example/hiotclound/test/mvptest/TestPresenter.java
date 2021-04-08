package com.example.hiotclound.test.mvptest;

import com.example.hiotclound.test.mvptest.model.User;

public class TestPresenter {

    private TestView view;

    public TestPresenter(TestView view) {
        this.view = view;
    }

    public void login(User user){
        if ("caiyu".equals(user.getUserName()) && "123".equals(user.getPassword())){
            view.showMessage("登录成功");
        }
        else {
            view.showMessage("登录失败");

        }
    }
}

