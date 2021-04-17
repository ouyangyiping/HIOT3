package com.example.hiotclound.test.dagger2test;

import java.security.PublicKey;

import dagger.Module;
import dagger.Provides;

/**
 * dagger2测试module类
 */
@Module
public class TestModule {
    @Provides
    public ThirdObj getThirdObj(){
        return new  ThirdObj();
    }
}
