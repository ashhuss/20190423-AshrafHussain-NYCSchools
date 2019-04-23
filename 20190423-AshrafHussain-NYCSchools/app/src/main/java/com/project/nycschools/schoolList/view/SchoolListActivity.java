package com.project.nycschools.schoolList.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.project.nycschools.R;
import com.project.nycschools.common.BaseActivity;
import com.project.nycschools.common.ConnectionDetector;
import com.project.nycschools.data.AppUtils;
import com.project.nycschools.schoolList.model.SchoolModel;
import com.project.nycschools.schoolList.presenter.SchoolListImp;
import com.project.nycschools.schoolList.presenter.SchoolListPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SchoolListActivity extends BaseActivity implements SchoolListView {
    private final String TAG = SchoolListActivity.class.getName();

    @BindView(R.id.rv_school_list)
    RecyclerView rvSchoolList;
    ConnectionDetector connectionDetector;
    @BindView(R.id.ll_no_internet)
    LinearLayout llNoInternet;
    SchoolListPresenter schoolListPresenter;
    @BindView(R.id.swipe_refresh_school_list)
    SwipeRefreshLayout swipeRefreshSchoolList;

    @Override
    protected int getLayout() {
        return R.layout.activity_school_list;
    }

    @Override
    protected void init() {

        //initializing the connection detector class
        connectionDetector = new ConnectionDetector(this);

        //setting up recycler view action
        rvSchoolList.setHasFixedSize(true);
        RecyclerView.LayoutManager schoolLayoutManager = new LinearLayoutManager(this);
        rvSchoolList.setLayoutManager(schoolLayoutManager);
        checkpoint();

        //pull to refresh action
        swipeRefreshSchoolList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // if user is offline displaying offline message
                if (!connectionDetector.isConnected()) {
                    llNoInternet.setVisibility(View.VISIBLE);
                    AppUtils.snackbar(swipeRefreshSchoolList, getString(R.string.no_internet_connection),
                            SchoolListActivity.this);
                    swipeRefreshSchoolList.setRefreshing(false);
                } else {
                    //reloading the school list data
                    schoolListPresenter.loadSchoolListData();
                    llNoInternet.setVisibility(View.GONE);
                    swipeRefreshSchoolList.setRefreshing(false);
                    AppUtils.snackbar(swipeRefreshSchoolList, getString(R.string.data_loaded),
                            SchoolListActivity.this);
                }
            }
        });
    }


    //getting the list response and setting up to adapter to recycler view
    @Override
    public void showSchoolListData(List<SchoolModel> schoolModelList) {
        SchoolListAdapter schoolListAdapter = new SchoolListAdapter(schoolModelList);
        rvSchoolList.setAdapter(schoolListAdapter);
        schoolListAdapter.notifyDataSetChanged();
        AppUtils.snackbar(swipeRefreshSchoolList, getString(R.string.data_loaded), this);
    }

    //on success response showing snack bar message
    @Override
    public void onSuccess() {
        AppUtils.snackbar(swipeRefreshSchoolList, getString(R.string.data_loaded), this);
    }

    //on error response showing snack bar message
    @Override
    public void onError() {
        AppUtils.snackbar(swipeRefreshSchoolList, getString(R.string.sth_wrong), this);
    }

    //checking device connectivity
    @Override
    public void checkpoint() {
        if (connectionDetector.isConnected()) {
            llNoInternet.setVisibility(View.GONE);

            //loading school list data
            schoolListPresenter = new SchoolListImp(this);
            schoolListPresenter.loadSchoolListData();

        } else {
            llNoInternet.setVisibility(View.VISIBLE);
            AppUtils.snackbar(swipeRefreshSchoolList, getString(R.string.no_internet_connection), this);
        }
    }

    //checking onResume state
    @Override
    protected void onResume() {
        super.onResume();
        checkpoint();
    }

    //click action connection retry.
    @OnClick(R.id.btn_retry)
    public void onViewClicked() {
        checkpoint();
    }
}
