package com.huatec.hiot_cloud.ui.login;

import android.text.TextUtils;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.test.networktest.LoginResultDTO;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * 登录模块presenter类
 */
 class LoginPresenter extends BasePresenter<LoginView> {

    @Inject
    DataManager dataManager;

    @Inject
    public LoginPresenter() {
    }

    /*
    登录
     */
    public void login(String email, String password) {
        subscribe(dataManager.login(email, password), new RequestCallback<ResultBase<LoginResultDTO>>() {
            @Override
            public void onNext(ResultBase<LoginResultDTO> resultBase) {
                if (resultBase.getStatus() == 1) {
                    //如果登录身份正确，弹出登录成功，跳转到主界面
                    if (resultBase != null && resultBase.getData() != null) {
                       //弹出登录成功
                        getView().showMessage("登录成功");

                        //跳转到主界面
                        getView().loginSucc();
                    }
                }else {
                    //如果登录身份错误，弹出服务端返回的错误信息
                    if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())) {
                        getView().showMessage(resultBase.getMsg());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getView().showMessage("当前网络无法访问，请稍后再试");
            }
        });
    }
}
