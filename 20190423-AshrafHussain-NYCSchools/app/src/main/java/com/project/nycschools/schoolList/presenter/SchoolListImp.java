package com.project.nycschools.schoolList.presenter;

import android.util.Log;

import com.project.nycschools.common.SetupRetrofit;
import com.project.nycschools.schoolList.model.ApiInterface;
import com.project.nycschools.schoolList.model.SchoolModel;
import com.project.nycschools.schoolList.view.SchoolListView;

import java.util.List;

import javax.inject.Inject;

import dagger.Component;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SchoolListImp implements SchoolListPresenter {

    public SchoolListImp(SchoolListView schoolListView) {
        this.schoolListView = schoolListView;
    }

    private SchoolListView schoolListView;

    @Override
    public void loadSchoolListData() {

        SetupRetrofit setupRetrofit = new SetupRetrofit();
        Retrofit retrofit = setupRetrofit.getRetrofit();

        final ApiInterface schoolApiInterface = retrofit.create(ApiInterface.class);
        schoolApiInterface.getSchoolData().enqueue(new Callback<List<SchoolModel>>() {
            private final String TAG = SchoolListImp.class.getName();

            @Override
            public void onResponse(Call<List<SchoolModel>> call, Response<List<SchoolModel>> response) {
                Log.d(TAG, "onResponseSchoolData: " + response.code());

                if (response.body() != null && response.code() == 200) {
                    schoolListView.showSchoolListData(response.body());
                    schoolListView.onSuccess();
                } else {
                    schoolListView.onError();
                }
            }

            @Override
            public void onFailure(Call<List<SchoolModel>> call, Throwable t) {
                schoolListView.onError();
            }
        });

    }
}
