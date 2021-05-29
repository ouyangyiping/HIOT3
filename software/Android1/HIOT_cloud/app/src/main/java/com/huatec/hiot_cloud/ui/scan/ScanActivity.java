package com.huatec.hiot_cloud.ui.scan;

import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.ui.base.BaseActivity;
import com.king.zxing.ViewfinderView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScanActivity extends BaseActivity<ScanView, ScanPresenter>  implements ScanView {

    @Inject
    ScanPresenter presenter;

    @BindView(R.id.surfaceView)
    SurfaceView surfaceView;

    @BindView(R.id.viewfinderView)
    ViewfinderView viewfinderView;

    @BindView(R.id.tv_light)
    TextView tvLight;

    @BindView(R.id.tv_album)
    TextView tvAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);
    }

    @Override
    public ScanPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }

    @OnClick({R.id.tv_light, R.id.tv_album})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_light:
                break;
            case R.id.tv_album:
                break;
        }
    }
}
