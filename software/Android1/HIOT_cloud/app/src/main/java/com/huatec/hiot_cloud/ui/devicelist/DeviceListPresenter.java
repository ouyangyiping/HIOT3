package com.huatec.hiot_cloud.ui.devicelist;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.data.bean.DeviceBean;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;
import com.huatec.hiot_cloud.utils.Constants;

import java.util.List;

import javax.inject.Inject;

/**
 * 设备列表功能Presenter层类
 */
class DeviceListPresenter extends BasePresenter<DeviceListView> {

    @Inject
    DataManager dataManager;

    @Inject
    public DeviceListPresenter() {
    }

    /**
     * 加载设备列表
     */
    public void loadDeviceList() {
        subscrib(dataManager.listBindedDevice(Constants.DEVICE_STATUS_BINDED), new RequestCallback<ResultBase<List<DeviceBean>>>() {
            @Override
            public void onNext(ResultBase<List<DeviceBean>> listResultBase) {
                super.onNext(listResultBase);
                listResultBase.getData();
                List<DeviceBean> deviceList = listResultBase.getData();
                getView().showDeviceList(deviceList);
            }
        });
    }
}
