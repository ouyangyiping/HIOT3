package com.example.hiotclound.test.dagger2test;

import com.example.hiotclound.test.mvptest.TsetMVPActivity;

import dagger.Component;

/**
 * 测试注入器接口
 */
@Component(modules = TestModule.class)
public interface PresenterComponent {
    void inject(TsetMVPActivity tsetMVPActivity);
}
