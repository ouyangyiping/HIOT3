package com.huatec.hiot_cloud.test.networktest;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.data.NetworkService;
import com.huatec.hiot_cloud.data.bean.UserBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestRxJavaActivity extends AppCompatActivity {

    private static final String TAG ="TestRxJavaActivity" ;
    private Retrofit retrofit;

    private NetworkService service;
    private EditText etToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rx_java);

        //editText
        etToken = findViewById(R.id.et_rxjava_token);

        //创建retrofit
        createRetrofit();

        //登录
        Button btnLogin = findViewById(R.id.btn_rxjava_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login("oy","abc123");

            }
        });

        //用户信息
        Button btnUserInfo = findViewById(R.id.btn_rxjava_userinfo);
        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserInfo(etToken.getText().toString());


            }
        });


        //修改邮箱
        Button btnUpdateEmail = findViewById(R.id.btn_rxjava_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdateEmail(etToken.getText().toString(),"oy857@qq.com");


            }
        });

        //注册
        Button btnRegister = findViewById(R.id.btn_rxjava_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    register();

            }
        });
    }

    /**
     * 修改邮箱
     * @param authorization
     * @param email
     */
    private void getdateEmail(String authorization, String email) {
        service.updateEmail(authorization,email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBase<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase<String> resultBase) {
                        Log.d(TAG, "onNext: " + resultBase.getMsg());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage(),e );

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 注册
     */
    private void register() {
        UserBean userBean = new UserBean();
        userBean.setUsername("oy14");
        userBean.setEmail("oy8570@qq.com");
        userBean.setPassword("abc123");
        userBean.setUserType("1");
        service.register(userBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBase<UserBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase<UserBean> resultBase) {
                        Log.d(TAG, "onNext: " + resultBase.getMsg());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage(),e );

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 获取用户信息
     * @param authorization
     */
    private void getUserInfo(String authorization) {
        Observable<ResultBase<UserBean>> observable = service.getUserInfo(authorization);
        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultBase<UserBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase<UserBean> resultBase) {
                        if (resultBase != null && resultBase.getData() != null){
                            UserBean userBean = resultBase.getData();
                            String str = String.format("用户：%s,email：%s",
                                    userBean.getUsername(), userBean.getEmail());
                            Toast.makeText(com.huatec.hiot_cloud.test.networktest.TestRxJavaActivity.this, str, Toast.LENGTH_SHORT).show();
                        }else if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())){
                            Toast.makeText(com.huatec.hiot_cloud.test.networktest.TestRxJavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 登录
     * @param userName
     * @param password
     */
    private void login(String userName, String password) {
        service.login(userName,password,"app")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBase<LoginResultDTO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase<LoginResultDTO> resultBase) {
                        if (resultBase != null && resultBase.getData() != null) {
                            LoginResultDTO loginResult = resultBase.getData();
                            Log.d(TAG, "onNext: " + loginResult.getTokenValue());
                            etToken.setText(loginResult.getTokenValue());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage(), e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void createRetrofit(){
        retrofit = new Retrofit.Builder().baseUrl(TestRetrofitService.basUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        service = retrofit.create(NetworkService.class);
    }
}
