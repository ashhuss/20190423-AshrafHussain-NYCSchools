package com.project.nycschools.schoolDetail;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.project.nycschools.R;
import com.project.nycschools.common.BaseActivity;
import com.project.nycschools.common.ConnectionDetector;
import com.project.nycschools.data.AppConstant;
import com.project.nycschools.data.AppUtils;
import com.project.nycschools.schoolList.model.SchoolModel;

import butterknife.BindView;
import butterknife.OnClick;

public class SatScoreActivity extends BaseActivity implements SatScoreView {
    private final String TAG = SatScoreActivity.class.getName();

    ConnectionDetector connectionDetector;
    @BindView(R.id.ll_no_internet)
    LinearLayout llNoInternet;
    @BindView(R.id.sv_detail_data)
    ScrollView scrollView;
    SchoolModel schoolModel;
    String schoolName;
    @BindView(R.id.tv_school_name)
    TextView tvSchoolName;
    @BindView(R.id.tv_take_takers)
    TextView tvTakeTakers;
    @BindView(R.id.tv_reading)
    TextView tvReading;
    @BindView(R.id.tv_writing)
    TextView tvWriting;
    @BindView(R.id.tv_maths)
    TextView tvMaths;



    @Override
    protected int getLayout() {
        return R.layout.activity_sat_score;
    }

    @Override
    protected void init() {
        //initializing the connection detector class
        connectionDetector = new ConnectionDetector(this);

        //Applied custom toolbar so removing the builtin action bar.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();

        //Checking if the intent is not null and assigning the value
        //to the views with school model object.

        if (intent.getExtras() != null) {
            schoolModel = (SchoolModel)
                    intent.getSerializableExtra(AppConstant.SCHOOL_DETAIL);
            schoolName = schoolModel.getSchoolName();
            tvSchoolName.setText(schoolName);
            tvTakeTakers.setText(schoolModel.getNumOfSatTestTakers());
            tvReading.setText(schoolModel.getSatCriticalReadingAvgScore());
            tvWriting.setText(schoolModel.getSatWritingAvgScore());
            tvMaths.setText(schoolModel.getSatMathAvgScore());
        }

        checkpoint();
    }

    //checking device connectivity
    @Override
    public void checkpoint() {
        if (connectionDetector.isConnected()) {
            llNoInternet.setVisibility(View.GONE);

        } else {
            llNoInternet.setVisibility(View.VISIBLE);
            AppUtils.snackbar(scrollView, getString(R.string.no_internet_connection), this);
        }
    }

    //checking onResume state
    @Override
    protected void onResume() {
        super.onResume();
        checkpoint();
    }

    //performing click actions for back and connection retry button.
    @OnClick({R.id.toolbar, R.id.btn_retry})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar:
                onBackPressed();
                break;
            case R.id.btn_retry:
                checkpoint();
                break;
        }
    }
}
