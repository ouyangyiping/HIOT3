package com.huatec.hiot_cloud.ui.scan;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;

import javax.inject.Inject;

class ScanPresenter extends BasePresenter<ScanView> {

    @Inject
    DataManager dataManager;

    @Inject
    public ScanPresenter() {
    }

    /**
     * 绑定设备
     * @param deviceId
     */
    public void bindDevice(String deviceId) {
        subscrib(dataManager.bindDevice(deviceId), new RequestCallback<ResultBase>() {
            @Override
            public void onNext(ResultBase resultBase) {
                super.onNext(resultBase);
                getView().showMessage("绑定成功");
                getView().binDeviceSucc();
            }
        });
    }
}
